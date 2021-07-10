/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smart.edi.akuh.smart.abacus.claims.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Ikatanyi
 */
@Data
@Entity
@Table(name = "STG_SLINK_CLAIMS",schema="ABACUS")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StgSlinkClaim implements Serializable {
        private static final long serialVersionUID = 1L;
        // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
        @Id
        @Column(name = "CENTRAL_ID", nullable = false)
        private Long centralId;
        @Column(name = "ID")
        private Long id;
        @Size(max = 60)

        @Column(name = "IP_ADDRESS", length = 100)
        private String ipAddress;
        @Size(max = 105)

        @Column(name = "INVOICE_NR", length = 120)
        private String invoiceNr;
        @Size(max = 60)
        @Column(name = "CLAIM_NR", length = 60)
        private String claimNr;

        @Column(name = "PATIENT_MEDICALAID_CODE", length = 30)
        private String patientMedicalaidCode;
        @Size(max = 75)
        @Column(name = "PATIENT_MEDICALAID_NR", length = 75)
        private String patientMedicalaidNr;
        @Size(max = 105)
        @Column(name = "POLICY_CURRENCY_CODE", length = 105)
        private String policyCurrencyCode;
        @Size(max = 105)
        @Column(name = "GLOBAL_ID", length = 105)
        private String globalId;
        @Column(name = "CLAIM_DATA")
        private String claimData;
        @Column(name = "IS_MGT")
        private BigInteger isMgt;
        @Column(name = "INTEG_ID")
        private BigInteger integId;
        @Column(name = "IS_ITEMIZED")
        private BigInteger isItemized;
        @Column(name = "insert_date")
        private LocalDate insertDate;
        private String backendProviderStatusMsg;
        private Integer backendProviderStatus;
        private LocalDateTime backendProviderFetchDate = LocalDateTime.now();
}
