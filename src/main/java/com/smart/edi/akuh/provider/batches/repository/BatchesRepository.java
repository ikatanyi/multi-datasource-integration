/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.batches.repository;


import com.smart.edi.akuh.provider.batches.model.Batch;
import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.provider.diagnosis.model.DiagnosisInterface;
import com.smart.edi.akuh.smart.abacus.claims.model.StgSlinkClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface BatchesRepository extends JpaRepository<Batch, Long>, JpaSpecificationExecutor<Batch> {

    @Query(value="select  d from Batch d where trunc(ibdBatchDate) >= trunc(sysdate-14) order by ibdBatchDate ASC")
    List<Batch>findBatchesByDate();



}
