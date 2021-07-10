package com.smart.edi.akuh.smart.abacus.diagnosis.repository;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface LogEdiDiagnosisRepository extends JpaRepository<LogEdiDiagnosis, Long> {
    @Query(value="SELECT d from LogEdiDiagnosis d where d.receiptNo =:receiptNo AND d.icdCode =:icdCode  and  d.providerKey='SKSP_505' and d.billDate =:billDate")
    Optional<Diagnosis>findDiagnosisByReceipNoAndProviderKey(@Param("receiptNo") String receiptNo, @Param("icdCode") String icdCode, @Param("billDate") LocalDate billDate);

}
