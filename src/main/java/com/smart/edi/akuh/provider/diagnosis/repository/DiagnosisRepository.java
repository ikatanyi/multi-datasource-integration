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
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {


    @Query(value = "select\n" +
            " RECEIPT_NO AS receiptNo,\n" +
            "\tBILL_DATE AS billDate,\n" +
            "\tPIN AS pin,\n" +
            "\tICD_CODE AS icdCode,\n" +
            "\tsmartopdiag.description AS description,\n" +
            "\tDIAG_TYPE AS diagType,\n" +
            "\t'999' AS user_id,\n" +
            "\tAPPOINTMENT_DATE AS appointmentDate,\n" +
            "\tCODING_DATE AS codingDate,\n" +
            "\tAPPT_NO AS apptNo,\n" +
            "\t'BACKEND_PROVIDER' AS viewSource\n" +
            " from smartopdiag where RECEIPT_NO in (:invoices)\n" +
            " UNION\n" +
            " select INVOICE_NO AS receiptNo,\n" +
            "\tINVOICE_DATE AS billDate,\n" +
            "    null as pin,\n" +
            "\tICD_CODE AS icdCode,\n" +
            "\tICD_DESC AS description,\n" +
            "\tICD_COMPLI_TYPE AS diagType,\n" +
            "\t'999' AS user_id,\n" +
            "\tINVOICE_DATE AS appointmentDate,\n" +
            "\tDIAGNOSIS_DATE AS codingDate,\n" +
            "    null as apptNo,\n" +
            "\t'BACKEND_PROVIDER' AS viewSource\n" +
            " from smartipdiag ip where INVOICE_NO in (:invoices)", nativeQuery = true)
    List<DiagnosisInterface> getDiagnosis(@Param("invoices") List invoices);

    

}
