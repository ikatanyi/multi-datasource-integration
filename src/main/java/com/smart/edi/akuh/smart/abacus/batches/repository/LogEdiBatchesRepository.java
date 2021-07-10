package com.smart.edi.akuh.smart.abacus.batches.repository;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.smart.abacus.batches.model.LogEdiBatches;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface LogEdiBatchesRepository extends JpaRepository<LogEdiBatches, Long> {
    Optional<LogEdiBatches>findByBatchNumber(String batchNo);
}
