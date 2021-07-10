/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.diagnosis.model;

import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Kelsas
 */
@Data
@Entity
@Table(name="SMARTOPDIAG", schema="SMART")
public class Diagnosis implements Serializable {
    @Id
    @Column(name="ROWID")
    String rowid;
    private LocalDate appointmentDate;
    private String apptNo;
    private LocalDate billDate;
    private LocalDate codingDate;
    private String description;
    private String diagType;
    private String icdCode;
    private String pin;
    private String receiptNo;

    public LogEdiDiagnosis toLogEdiDiagnosis(){
        LogEdiDiagnosis logEdiDiagnosis = new LogEdiDiagnosis();
        logEdiDiagnosis.setAppointmentDate(this.getAppointmentDate());
        logEdiDiagnosis.setApptNo(this.getApptNo());
        logEdiDiagnosis.setBillDate(this.getBillDate());
        logEdiDiagnosis.setCodingDate(this.getCodingDate());
        logEdiDiagnosis.setDescription(this.getDescription());
        logEdiDiagnosis.setDiagType(this.getDiagType());
        logEdiDiagnosis.setIcdCode(this.getIcdCode());
        logEdiDiagnosis.setPin(this.getPin());
        logEdiDiagnosis.setReceiptNo(this.getReceiptNo());
        return logEdiDiagnosis;
    }

}
