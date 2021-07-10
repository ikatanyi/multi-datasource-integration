package com.smart.edi.akuh.provider.claims.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="SMART_DETAILED_OP")
public class Invoice implements Serializable {
    @Transient
    private Long id;
    private LocalDate ctdChrgDt;
    @Id
    private String invoiceNo;
    private String ctdPin;
    private String ctdItemDes;
    private String ctdItemCd;
    private String itemGroup;
    private Double qty;
    private Double patAmt;
    private Double spnrAmt;
    private Double totalPatAmt;
    private Double totalSpnrAmt;
    private String visitId;
    private Double issRef;

}
