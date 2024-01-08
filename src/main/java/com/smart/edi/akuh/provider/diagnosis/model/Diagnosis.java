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
import java.time.LocalDateTime;
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
    private String appointmentDate;
    private String apptNo;
    private String billDate;
    private String codingDate;
    private String description;
    private String diagType;
    private String icdCode;
    private String pin;
    private String receiptNo;
//
//    HEADER
//            receipt_no
//    bill_date
//            pin
//    icd_code
//            description
//    diag_type
//            provider_key
//    user_id
//            appointment_date
//    coding_date
//            appt_no
//    view_source


    public LogEdiDiagnosis toLogEdiDiagnosis(){
        LogEdiDiagnosis logEdiDiagnosis = new LogEdiDiagnosis();
        logEdiDiagnosis.setAppointmentDate(LocalDateTime.parse(this.getAppointmentDate()!=null?this.getAppointmentDate().replace(" ","T"):LocalDateTime.now().toString()).toLocalDate());
        logEdiDiagnosis.setApptNo(this.getApptNo());
        logEdiDiagnosis.setBillDate(LocalDateTime.parse(this.getBillDate()!=null?this.getBillDate().replace(" ","T"):LocalDateTime.now().toString()).toLocalDate());
        logEdiDiagnosis.setCodingDate(LocalDateTime.parse(this.getCodingDate()!=null?this.getCodingDate().replace(" ","T"):LocalDateTime.now().toString()).toLocalDate());
        logEdiDiagnosis.setDescription(this.getDescription());
        logEdiDiagnosis.setDiagType(this.getDiagType());
        logEdiDiagnosis.setIcdCode(this.getIcdCode());
        logEdiDiagnosis.setPin(this.getPin());
        logEdiDiagnosis.setReceiptNo(this.getReceiptNo());
        return logEdiDiagnosis;
    }

}
