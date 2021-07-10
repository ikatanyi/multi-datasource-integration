package com.smart.edi.akuh.provider.claims.model;

import java.util.Date;

/**
 *
 * @author Kelsas
 */
public class Copay {

    private double charge = 0;
    private Date charge_date;
    private String type = "";
    private String reference = "";

    public Copay() {
    }

    public Copay(String type, double charge) {
        this.charge = charge;
        this.type = type;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public Date getChargeDate() {
        return charge_date;
    }

    public void setChargeDate(Date charge_date) {
        this.charge_date = charge_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    enum Type {
        SELF_PAY, NHIF;
    }
}
