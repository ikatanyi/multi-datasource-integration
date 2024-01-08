package com.smart.edi.akuh.smart.abacus.diagnosis.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="LOG_EDI_DIAGNOSIS", schema="ABACUS")
public class LogEdiDiagnosis implements Serializable {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "ABACUS.LOG_EDI_DIAG_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String receiptNo;
    private LocalDate billDate;
    private LocalDate appointmentDate;
    private LocalDate codingDate;
    private String apptNo;
    private String pin;
    private String icdCode;
    private String description;
    private String diagType;
    private Integer isTransmitted;
    private String providerKey="SKSP_505";
    private Integer responseId;
    private String responseObjectcode;
    private LocalDateTime postTime;
    private String errorDesc;
    private Integer origClaimId;
    private LocalDateTime insertTime;
    private String viewSource="DIAG_SOURCE";
    private Boolean status;
    private LocalDateTime statusTime;
    private String statusMsg;
}
