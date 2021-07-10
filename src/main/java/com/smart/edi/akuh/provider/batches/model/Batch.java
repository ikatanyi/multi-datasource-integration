/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.batches.model;

import com.smart.edi.akuh.smart.abacus.batches.model.LogEdiBatches;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Kelsas
 */
@Data
@Entity
@Table(name="SMARTBATCH", schema="SMART")
public class Batch implements Serializable {
    @Id
    @Column(name="ROWID")
    String rowid;

    private String cahDisBatchNo;;
    private String voucherNo;
    private LocalDate ibdBatchDate;

    public LogEdiBatches toLogEdiBatches(){
        LogEdiBatches logEdibatches = new LogEdiBatches();
        logEdibatches.setBatchNumber(this.getCahDisBatchNo());
        logEdibatches.setInvoiceNr(this.getVoucherNo());
        logEdibatches.setBatchDate(this.getIbdBatchDate());
        return logEdibatches;
    }

}
