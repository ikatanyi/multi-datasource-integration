/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.claims.repository;

import com.smart.edi.akuh.provider.claims.model.Invoice;
import com.smart.edi.akuh.provider.claims.model.InvoicelineInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Ikatanyi
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    @Query(value="SELECT TOTAL_SPNR_AMT as totalSpnrAmt,SPNR_AMT AS spnrAmt,CTD_CHRG_DT AS ctdChrgDt,CTD_ITEM_CD AS ctdItemCd,CTD_ITEM_DES AS ctdItemDes,QTY as qty,ITEM_GROUP AS itemGroup FROM SMART_DETAILED_OP WHERE invoice_no = :invoiceNo UNION SELECT TOTAL_SPNR_AMT as totalSpnrAmt,SPNR_AMT AS spnrAmt,CTD_CHRG_DT AS ctdChrgDt,CTD_ITEM_CD AS ctdItemCd,CTD_ITEM_DES AS ctdItemDes,QTY as qty,ITEM_GROUP AS itemGroup FROM SMART_DETAILED_IP WHERE  invoice_no = :invoiceNo", nativeQuery=true)
    List<InvoicelineInterface> findInvoices(@Param("invoiceNo") final String invoiceNo);

}
