/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.smart.abacus.claims.repository;


import com.smart.edi.akuh.smart.abacus.claims.model.StgSlinkClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface StgSlinkClaimRepository extends JpaRepository<StgSlinkClaim, Long>, JpaSpecificationExecutor<StgSlinkClaim> {

    @Query(value="Select invoiceNr from StgSlinkClaim d where d.backendProviderStatus=:status AND insertDate>= to_date('15-05-2021','dd-mm-yyyy') AND d.ipAddress='SKSPKDN_4' AND ROWNUM<=1000")
    List<String>findPendingInvoices(@Param("status") Integer status);
    @Query(value="Select d from StgSlinkClaim d where d.invoiceNr=:invoiceNr AND d.ipAddress=:ipAddress AND ROWNUM=1")
    StgSlinkClaim findByInvoiceNr(@Param("invoiceNr") String invoiceNr, @Param("ipAddress") String ipAddress);
    @Modifying
    @Query(value = "UPDATE abacus.Stg_Slink_Claims s set s.is_itemized=:value WHERE s.central_Id=:centralId", nativeQuery = true)
    void updateStgSlinkIsItemized(@Param("value") Integer value, @Param("centralId") Long centralId);
}
