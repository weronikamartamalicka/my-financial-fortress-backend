package com.restapi.financialfortressbackend.scheduler;

import com.restapi.financialfortressbackend.client.*;
import com.restapi.financialfortressbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledValueUpdateService {

    private final GoldClient goldClient;
    private final GoldValuationRepository goldValuationRepository;
    private final BondsQuotedOnTheMarketClient bondsQuotedClient;
    private final BondsQuotedValuationRepository bondsQuotedRepository;
    private final DevelopedMarketStocksClient developedMarketClient;
    private final DevelopedMarketValuationRepository developedMarketRepository;
    private final EmergingMarketStocksClient emergingMarketClient;
    private final EmergingMarketValuationRepository emergingMarketRepository;
    private final InflationIndexedBondsClient inflationIndexedBondsClient;
    private final InflationValuationRepository inflationValuationRepository;
    private final InflationClient inflationClient;

    @Scheduled(cron = "0 0 12 * * *")
    public void updateGoldValue() {

        if(Optional.ofNullable(goldValuationRepository.findByDate(LocalDate.now())).isPresent() == false) {
            goldClient.getGoldSaleValue();
        }
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void updateEmergingMarketValue() {

        if(Optional.ofNullable(emergingMarketRepository.findByDate(LocalDate.now())).isPresent() == false) {
            emergingMarketClient.updateEmergingMarketStockValuation();
        }
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void updateDevelopedMarketValue() {

        if(Optional.ofNullable(developedMarketRepository.findByDate(LocalDate.now())).isPresent() == false) {
            developedMarketClient.updateDevelopedMarketStockValuation();
        }
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void updateBondsQuoted() {

        if(Optional.ofNullable(bondsQuotedRepository.findByDate(LocalDate.now())).isPresent() == false) {
            bondsQuotedClient.updateBondsQuotedOnTheMarketValuation();
        }
    }

    @Scheduled(cron = "0 0 12 01 * *")
    public void updateBondsIndexed() {

        inflationIndexedBondsClient.updateInflationIndexedBondsRate();
        inflationClient.updateInflationRate();
    }

}
