/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.diagnosis.model;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author Kelsas
 */

@Data
public class Diagnosis_Log {
    private int ID;
    public String ErrorDescription;
    public String InvoiceNumber;
    public String ProviderKey = "SKSP_505";
    public String ClaimID;
    public Integer responseID;
    public String ResponseObjectCode;
    public Date InsertDate;
    private Date DiagnosisDate; 
    private Integer isTrasmitted;
    private String icdcode;


}
