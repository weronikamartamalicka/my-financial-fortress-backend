package com.restapi.financialfortressbackend.service;

import com.restapi.financialfortressbackend.domain.ModelPortfolioInvestment;
import com.restapi.financialfortressbackend.exception.*;
import com.restapi.financialfortressbackend.repository.ModelPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class ModelPortfolioService {

    private static final BigDecimal GOLD_PERCENTAGE = new BigDecimal(0.1);
    private static final BigDecimal BONDS_QUOTED_PERCENTAGE = new BigDecimal(0.2);
    private static final BigDecimal BONDS_INDEXED_PERCENTAGE = new BigDecimal(0.4);
    private static final BigDecimal DEVELOPED_MARKET_PERCENTAGE = new BigDecimal(0.15);
    private static final BigDecimal EMERGING_MARKET_PERCENTAGE = new BigDecimal(0.15);
    private final static BigDecimal FACE_VALUE = new BigDecimal(1000);

    @Autowired
    GoldService goldService;
    @Autowired
    DevelopedMarketStocksService developedMarketService;
    @Autowired
    InflationIndexedBondsService inflationIndexedBondsService;
    @Autowired
    BondsQuotedOnTheMarketService bondsQuotedOnTheMarketService;
    @Autowired
    EmergingMarketStocksService emergingMarketService;
    @Autowired
    ModelPortfolioRepository modelPortfolioRepository;

    ModelPortfolioInvestment modelPortfolio;

    public void calculateComposition(BigDecimal investmentCapital) throws DevelopedMarketStocksNotFoundException
            , EmergingMarketStocksNotFoundException, GoldNotFoundException, InflationIndexedNotFoundException, BondsQuotedNotFoundException {

        modelPortfolio = new ModelPortfolioInvestment();
        modelPortfolio.setDate(LocalDate.now());

        calculateGoldComposition(investmentCapital);
        calculateDevelopedMarketComposition(investmentCapital);
        calculateEmergingMarketComposition(investmentCapital);
        calculateBondsQuotedComposition(investmentCapital);
        calculateBondsIndexedComposition(investmentCapital);
    }

    public void calculateGoldComposition(BigDecimal investmentCapital) throws GoldNotFoundException {

        BigDecimal goldSale = goldService.findByDate(LocalDate.now()).getSaleValuation();
        BigDecimal goldPurchase = goldService.findByDate(LocalDate.now()).getPurchaseValuation();
        BigDecimal goldModelValuation = investmentCapital.multiply(GOLD_PERCENTAGE);

        if(goldModelValuation.compareTo(goldSale) == -1) {
            modelPortfolio.setGoldValue(goldSale);
            BigDecimal actualPercentage = goldSale.divide(investmentCapital, 2, RoundingMode.HALF_UP);
            modelPortfolio.setGoldPercentage(actualPercentage);
            goldService.findByDate(LocalDate.now()).setQuantity(new BigDecimal(1));
            goldService.findByDate(LocalDate.now()).setEntireValue(goldPurchase);

        } else {
            BigDecimal coinsQuantity = goldModelValuation.divide(goldSale, 0, RoundingMode.DOWN);

            goldService.findByDate(LocalDate.now()).setQuantity(coinsQuantity);
            BigDecimal actualValue = coinsQuantity.multiply(goldPurchase);
            goldService.findByDate(LocalDate.now()).setEntireValue(actualValue);
            BigDecimal actualPercentage = actualValue.divide(investmentCapital, 2, RoundingMode.HALF_UP);
            modelPortfolio.setGoldPercentage(actualPercentage);
        }
    }

    public void calculateDevelopedMarketComposition(BigDecimal investmentCapital) throws DevelopedMarketStocksNotFoundException {

        BigDecimal saleValuation = developedMarketService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = developedMarketService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal developedMarketModelValuation = investmentCapital.multiply(DEVELOPED_MARKET_PERCENTAGE);
        BigDecimal capital = developedMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);
        BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        modelPortfolio.setDevelopedMarketValue(entireStocksValuation);
        modelPortfolio.setDevelopedMarketPercentage(actualPercentage);
        developedMarketService.findByDate(LocalDate.now()).setQuantity(stocksNumber);
        developedMarketService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);
    }

    public void calculateEmergingMarketComposition(BigDecimal investmentCapital) throws EmergingMarketStocksNotFoundException, DevelopedMarketStocksNotFoundException {

        BigDecimal saleValuation = emergingMarketService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = emergingMarketService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal emergingMarketModelValuation = investmentCapital.multiply(EMERGING_MARKET_PERCENTAGE);
        BigDecimal capital = emergingMarketModelValuation.subtract(commissionValue);

        BigDecimal stocksNumber = capital.divide(saleValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = stocksNumber.multiply(saleValuation);
        BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        modelPortfolio.setEmergingMarketValue(entireStocksValuation);
        modelPortfolio.setEmergingMarketPercentage(actualPercentage);
        emergingMarketService.findByDate(LocalDate.now()).setQuantity(stocksNumber);
        emergingMarketService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);
    }

    public void calculateBondsIndexedComposition(BigDecimal investmentCapital) throws InflationIndexedNotFoundException {

        BigDecimal remainingCapital = investmentCapital
                .subtract(modelPortfolio.getBondsQuotedValue())
                .subtract(modelPortfolio.getDevelopedMarketValue())
                .subtract(modelPortfolio.getGoldValue())
                .subtract(modelPortfolio.getEmergingMarketValue());

        BigDecimal commissionValue = inflationIndexedBondsService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal price = inflationIndexedBondsService.findByDate(LocalDate.now()).getPrice();
        BigDecimal bondsQuotedModelValuation = remainingCapital.multiply(BONDS_INDEXED_PERCENTAGE);
        BigDecimal capital = bondsQuotedModelValuation.subtract(commissionValue);

        BigDecimal bondsNumber = capital.divide(price, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(price);
        BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        modelPortfolio.setBondsIndexedValue(entireStocksValuation);
        modelPortfolio.setBondsIndexedPercentage(actualPercentage);
        inflationIndexedBondsService.findByDate(LocalDate.now()).setQuantity(bondsNumber);
        inflationIndexedBondsService.findByDate(LocalDate.now()).setEntireValue(entireStocksValuation);

    }

    public void calculateBondsQuotedComposition(BigDecimal investmentCapital) throws BondsQuotedNotFoundException {

        BigDecimal saleValuation = bondsQuotedOnTheMarketService.findByDate(LocalDate.now()).getValuation();
        BigDecimal commissionValue = bondsQuotedOnTheMarketService.findByDate(LocalDate.now()).getCommissionRate();
        BigDecimal oneBondValuation = saleValuation.divide(new BigDecimal(100)).multiply(FACE_VALUE);
        BigDecimal bondsQuotedModelValuation = investmentCapital.multiply(BONDS_QUOTED_PERCENTAGE);
        BigDecimal capital = bondsQuotedModelValuation.subtract(commissionValue);

        BigDecimal bondsNumber = capital.divide(oneBondValuation, 0, RoundingMode.DOWN);
        BigDecimal entireStocksValuation = bondsNumber.multiply(oneBondValuation);
        BigDecimal actualPercentage = entireStocksValuation.divide(investmentCapital, 2, RoundingMode.HALF_UP);

        modelPortfolio.setBondsQuotedValue(entireStocksValuation);
        modelPortfolio.setBondsQuotedPercentage(actualPercentage);
        bondsQuotedOnTheMarketService.findByDate(LocalDate.now()).setQuantity(bondsNumber);
        bondsQuotedOnTheMarketService.findByDate(LocalDate.now()).setEntireValuation(entireStocksValuation);

    }

}
