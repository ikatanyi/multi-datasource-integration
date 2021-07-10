package com.smart.edi.akuh.smart.abacus.batches.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="LOG_EDI_BATCHES", schema="ABACUS")
public class LogEdiBatches implements Serializable {
    @Id
    private Long id;
    private String providerKey="SKSP_505";
    private String invoiceNr;
    private String batchNumber;
    private LocalDate batchDate;
    private Boolean status;

}
