package com.smart.edi.akuh.provider.claims.data;

import com.smart.edi.akuh.provider.claims.model.Copay;
import com.smart.edi.akuh.provider.claims.model.Invoiceline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kelsas
 */
public class InvoiceData implements Serializable {

    private String invoiceNumber;
    private Date invoiceDate;

    private ArrayList<Copay> copays;

    private ArrayList<Invoiceline> lines;  
    List<DiagnosisData>diagnosis;

    public InvoiceData() {
    }

    public InvoiceData(String invoiceNo) {
        this.invoiceNumber = invoiceNo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<Copay> getCopays() {
        if (copays == null) {
            copays = new ArrayList<>();
        }
        return copays;
    }

    public void setCopays(ArrayList<Copay> copays) {
        this.copays = copays;
    }

    public List<DiagnosisData> getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(List<DiagnosisData> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ArrayList<Invoiceline> getLines() {
        if (lines == null) {
            lines = new ArrayList();
        }
        return lines;
    }

    public double getLineTotal() {
        double amt = 0;
        amt = getLines().stream().map((line) -> line.getAmount()).reduce(amt, (accumulator, _item) -> accumulator + _item);
        return amt;
    }

    public double getCopayTotal() {
        double amt = 0;
        amt = getCopays()
                .stream()
                .map(c -> c.getCharge())
                .reduce(amt, (accumulator, _item) -> accumulator + _item);
        return amt;
    }

    public void setLines(ArrayList<Invoiceline> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", copays=" + copays + ", lines=" + lines + '}';
    }

}
