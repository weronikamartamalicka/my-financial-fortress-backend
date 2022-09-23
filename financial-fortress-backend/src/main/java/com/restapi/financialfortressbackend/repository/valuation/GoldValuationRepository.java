package com.restapi.financialfortressbackend.repository.valuation;

import com.restapi.financialfortressbackend.domain.valuation.GoldValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface GoldValuationRepository extends CrudRepository< GoldValuation, LocalDate> {
    @Override
    List<GoldValuation> findAll();
}
