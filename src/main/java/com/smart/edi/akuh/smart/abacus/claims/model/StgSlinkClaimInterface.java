/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smart.edi.akuh.smart.abacus.claims.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;

/**
 *
 * @author Ikatanyi
 */

public interface StgSlinkClaimInterface {
    public Long getId();
    public String getNamesAsIs();
    public String getCustId();
    public Long getCentralId();    
    public Clob getClaimData();
    public String getPatientNumber();
    public String getMedicalaidNr();
    public String getSmartCode();
    public BigInteger getPoolNr();
    public BigDecimal getClaimAmount();
    public String getGlobalId();
    public String getClaimProvCode();
    public String getPolId();
    public String getPointId();
    public String getPatientMedicalaidCode();    
}
