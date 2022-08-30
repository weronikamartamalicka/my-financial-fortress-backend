package com.restapi.financialfortressbackend.repository;

import com.restapi.financialfortressbackend.domain.GoldValuation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface GoldValuationRepository extends CrudRepository< GoldValuation, LocalDate> {

    GoldValuation findByDate(LocalDate date);
}
