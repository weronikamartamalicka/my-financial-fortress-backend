package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface GoldValuationRepository extends CrudRepository< GoldValuation, LocalDate> {

    GoldValuation findByDate(LocalDate date);

    @Override
    List<GoldValuation> findAll();

    GoldValuation findFirstByDate(LocalDate date);
}
