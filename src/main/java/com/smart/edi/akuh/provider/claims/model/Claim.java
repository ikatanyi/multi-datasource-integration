package com.smart.edi.akuh.provider.claims.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="smart_trans_opip")
public class Claim implements Serializable {
    private String partyCode;
    private String creditParty;
    private String akNo;
    private String patName;
    private String invoiceStatus;
    @Id
    private String billNo;
    private LocalDate voucherDate;
    private String admissionNo;
    private Double outstandingAmt;
    private Double paymentAmt;
    private Double totDepositAmt;
    private Double invoiceAmt;
    private String refno;
    private String batchNo;
    private LocalDate batchDate;
    private String entCd;
    private String userId;
    private String userName;
    private String userDept;
    private String sourceLocation;
    private String entitlement;

    public ClaimRequest toClaimRequest() {
        ClaimRequest request = new ClaimRequest();
        request.setPayerCode(this.getPartyCode());
        request.setPayerName(this.getCreditParty());
        request.setMemberNumber(this.getEntCd());
        request.setPatientName(this.getPatName());
        request.setPatientNumber(this.getAkNo());
        request.setLocationCode(this.getSourceLocation());
        request.setLocationName(this.getSourceLocation());
        request.setSchemeCode(this.getEntCd());
        request.setSchemeName(this.getEntitlement());
//        request.setMemberNumber("");
        request.setVisitStart(this.getVoucherDate().toString());
        request.setVisitEnd(this.getVoucherDate().toString());
        request.setPaymentAmount(this.getPaymentAmt());
        request.setDispatchNo(this.getBatchNo());
        request.setInvoiceDate(String.valueOf(this.getVoucherDate()));
        request.setVisitNumber(this.getBillNo());
        request.setOutstandingAmount(this.getTotDepositAmt());
        request.setInvoiceNo(this.getBillNo());
        request.setServiceType(this.getAdmissionNo()!=null?"IP":"OP");
        return request;
    }
}
