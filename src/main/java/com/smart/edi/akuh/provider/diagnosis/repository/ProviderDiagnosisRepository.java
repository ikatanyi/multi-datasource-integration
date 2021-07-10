/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.diagnosis.repository;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.provider.diagnosis.model.DiagnosisInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface ProviderDiagnosisRepository extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {

    @Query(value = "SELECT d from Diagnosis d WHERE d.codingDate >=  trunc(sysdate-14)")
    List<Diagnosis> findDiagnosisByDate();

    @Query(value = "select b.invoice_amt  as receiptNO, A.APPT_DATE as billDate, a.APPT_DATE as appointmentDate, B.VOUCHER_DATE as codingDate , \n"
            + "a.APPT_NO as apptNo ,a.PIN as pin  , a.DOC_CASE_DOC_CD as icdCode, 'SECONDARY' AS diagType  , A.DESCRIPTION AS description \n"
            + "from CARE.SMART_DIAG_OUTREACH A JOIN smart_trans_opip B ON A.PIN = B.AK_NO where lENGTH(description) > 4 and B.VOUCHER_DATE >=  trunc(sysdate-14)", nativeQuery = true)
    List<DiagnosisInterface> getSateLiteDiagNosis();

    

}
