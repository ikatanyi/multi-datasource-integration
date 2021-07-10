/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.claims.repository;

import com.smart.edi.akuh.provider.claims.model.Claim;
import com.smart.edi.akuh.provider.claims.model.DiagnosisInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface ClaimRepository extends JpaRepository<Claim, Long>, JpaSpecificationExecutor<Claim> {
    @Query(value="SELECT d FROM Claim d WHERE d.billNo in :invoices AND d.partyCode='46026'")
    List<Claim> findClaims(@Param("invoices") List<String>invoices);
    
    @Query(value = "SELECT \n"
            + "ip.icd_code AS code,\n"
            + "case when ip.icd_compli_type='PRIMARY' then 'true' else 'false' end AS isPrimary,\n"
            + "ip.icd_desc AS name\n"
            + "FROM SMARTIPDIAG ip WHERE INVOICE_NO=:invoiceNo\n"
            + "UNION \n"
            + "select op.icd_code AS code,\n"
            + "case when op.diag_type='PRIMARY' then 'true' else 'false' end AS isPrimary, \n"
            + "op.description AS name \n"
            + "from SMARTOPDIAG op where RECEIPT_NO=:invoiceNo", nativeQuery = true)
    List<DiagnosisInterface> findDiagnosis(@Param("invoiceNo") String invoiceNo);
    
}
