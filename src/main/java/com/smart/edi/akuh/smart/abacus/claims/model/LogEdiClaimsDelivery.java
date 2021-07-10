package com.smart.edi.akuh.smart.abacus.claims.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="LOG_EDI_CLAIMS_DELIVERY", schema="ABACUS")
public class LogEdiClaimsDelivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String providerKey;
    private String invoiceNr;
    private LocalDate claimDate;
    private String jsonString;
}
