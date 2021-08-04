package com.smart.edi.akuh.provider.claims.model;

import java.time.LocalDate;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Kelsas
 */
@Data
public class Invoiceline {

    private String itemCode;
    private String itemName;
    private String chargeDate;
    private Double unitPrice;
    private Double quantity;
    private Double amount;
    private String servicePoint;
    private Date billingDatetime;
    private Boolean cancellation;
    private Double netAmount;

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        this.cancellation = (quantity < 0);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setServicePoint(String servicePoint) {
        this.servicePoint = servicePoint;
    }

    public Date getBillingDatetime() {
        return billingDatetime;
    }

    public void setBillingDatetime(Date billingDatetime) {
        this.billingDatetime = billingDatetime;
    }

    public double lineTotal() {
        return (unitPrice * quantity);
    }
}
