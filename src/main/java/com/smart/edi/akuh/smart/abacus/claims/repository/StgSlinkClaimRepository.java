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

//    @Query(value="Select d.invoice_Nr from stg_slink_claims d join fin_providers a on d.ip_address=a.provider_key where  d.backend_provider_status=:status AND d.insert_Date>= to_date('15-05-2021','dd-mm-yyyy') AND a.parent_provider='SKSPKDN_4' AND ROWNUM<=1000",nativeQuery = true)
@Query(value="select st.invoice_nr\n" +
        "from stg_slink_claims st\n" +
        "join fin_providers pr on st.ip_address=pr.provider_key and st.claim_type=0 and st.insert_date>='15-may-2021'\n" +
        "and st.global_id like 'KE%' and st.backend_provider_status=:status\n" +
        "join fin_client_details cd on pr.parent_provider=cd.smart_code and cd.prov_id is not null and cd.is_edi_backendviews=1\n" +
        "and cd.is_edi_backend=1 and cd.smart_code=:providerKey", nativeQuery = true)
List<String> findPendingInvoices(@Param("providerKey") String providerKey, @Param("status") Integer status);


    @Query(value="Select d from StgSlinkClaim d where d.invoiceNr=:invoiceNr AND d.backendProviderStatus=:status AND ROWNUM<=1")
    StgSlinkClaim findByInvoiceNr(@Param("invoiceNr") String invoiceNr, @Param("status") Integer status);

    @Modifying
    @Query(value = "UPDATE abacus.Stg_Slink_Claims s set s.is_itemized=:value WHERE s.central_Id=:centralId", nativeQuery = true)
    void updateStgSlinkIsItemized(@Param("value") Integer value, @Param("centralId") Long centralId);
}
