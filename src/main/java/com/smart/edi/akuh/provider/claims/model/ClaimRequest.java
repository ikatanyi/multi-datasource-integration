package com.smart.edi.akuh.provider.claims.model;

import com.smart.edi.akuh.EDIConstant;
import com.smart.edi.akuh.provider.claims.data.DiagnosisData;
import com.smart.edi.akuh.util.ICD10;
import com.smart.edi.akuh.provider.claims.data.InvoiceData;
import com.smart.edi.akuh.util.RoundUtil;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kelsas
 */
 @Data
public class ClaimRequest implements Serializable {

    //TODO :: this will represent a full invoice that is ready to be submitted
    private String id;
    private String invoiceNo="";
    private String referenceNumber="";
    private String payerCode="";
    private String payerName="";
    private String patientName="";
    private String patientNumber="";
    private String memberNumber="";
    private String serviceType="";
    private String locationCode="";
    private String locationName="";
    private String schemeCode="";
    private String schemeName="";
    private String visitNumber="";
    private String visitStart="";
    private String visitEnd="";
    private ArrayList<ICD10> ICD10Codes;
    private ArrayList<InvoiceData> invoices;
    private String dispatchNo="";
    private String invoiceDate="";
    private String claimId="";
    private double paymentAmount;
    private double outstandingAmount;
    private List<DiagnosisData>diagnosis;

    public ClaimRequest() {
    }

    public ClaimRequest(String id, String invoiceNo, String transno) {
        this.id = id;
        this.invoiceNo = invoiceNo;
        this.referenceNumber = transno;
    }


    public ArrayList<ICD10> getICD10Codes() {
        if (ICD10Codes == null) {
            ICD10Codes = new ArrayList<>();
        }
        return ICD10Codes;
    }

    public ArrayList<InvoiceData> getInvoices() {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    public double getGrossAmount() {
        double amt = 0;
        amt = getInvoices()
                .stream()
                .map((b) -> b.getLineTotal())
                .reduce(amt, (accumulator, _item) -> accumulator + _item);
        return RoundUtil.round(amt,2);
    }

    public double getNetAmount() {
        double amt = 0;
        amt = getInvoices()
                .stream()
                .map((b) -> b.getCopayTotal())
                .reduce(amt, (accumulator, _item) -> accumulator + _item);

        return RoundUtil.round((getGrossAmount() - amt),2);
    }

    public Set<Copay> getCopays() {
        Set<Copay> list = new HashSet();
        getInvoices().forEach((inv) -> {
            inv.getCopays()
                    .forEach(c -> list.add(c));
        });
        if (list.isEmpty()) {
            list.add(new Copay("", 0));
        }

        return list;
    }
    

    public String claimToJson() {
        JSONObject claim = new JSONObject();

        claim.put("claim_code", this.getVisitNumber());
        claim.put("payer_code", this.getPayerCode());
        claim.put("payer_name", this.getPayerName());
        claim.put("amount", this.getGrossAmount()); //invoice totals
        claim.put("gross_amount", this.getNetAmount());
        claim.put("batch_number", "");

        claim.putOpt("dispatch_date", JSONObject.NULL);
        claim.put("patient_number", this.getPatientNumber());
        claim.put("patient_name", this.getPatientName());
        claim.put("location_code", this.getLocationCode());
        claim.put("location_name", this.getLocationName());
        claim.put("scheme_code", this.getSchemeCode());
        claim.put("scheme_name", this.getSchemeName());
        claim.put("member_number", this.getMemberNumber());
        claim.put("visit_number", this.getVisitNumber());
        claim.put("visit_start", this.getVisitStart());
        claim.put("visit_end", this.getVisitEnd());
        claim.put("currency", EDIConstant.CURRENCY);

        //diagnosis
        JSONArray diagnosis = new JSONArray();
        JSONObject pd = new JSONObject();
        pd.put("coding_standard", JSONObject.NULL);
        pd.put("code", JSONObject.NULL);
        pd.put("name", JSONObject.NULL);
        pd.put("is_primary", JSONObject.NULL);
        diagnosis.put(pd);

        claim.put("diagnosis",this.getDiagnosis().isEmpty()?diagnosis:this.getDiagnosis().isEmpty());

        //pre auth
        JSONArray preauth = new JSONArray();
        JSONObject pa = new JSONObject();
        pa.put("code", "");
        pa.put("amount", 0);
        pa.put("authorized_by", "");
        pa.put("message", "");
        preauth.put(pa);
        claim.put("pre_authorization", preauth);
        //admission
        JSONArray admis = new JSONArray();
        JSONObject a = new JSONObject();
        a.put("admit_date", this.getVisitStart());
        a.put("discharge_date", this.getVisitStart());
        a.put("discharge_summary", "");
        admis.put(a);
        claim.put("admission", admis);
        //invoices

        JSONArray invoices = new JSONArray();

        JSONObject invoice = new JSONObject();
        invoice.put("amount", this.getGrossAmount());
        invoice.put("gross_amount", this.getNetAmount());
        invoice.put("invoice_date", this.getVisitStart());
        invoice.put("invoice_number", this.getVisitNumber());
        invoice.put("service_type", this.getServiceType());

        JSONArray pmodifier = new JSONArray();

        this.getCopays().stream().map((c) -> {
            JSONObject modifier = new JSONObject();
            modifier.putOpt("type", c.getType());
            modifier.putOpt("amount", c.getCharge());
            modifier.putOpt("ref_number", c.getReference());
            return modifier;
        }).forEachOrdered((modifier) -> {
            pmodifier.put(modifier);
        });

        if (pmodifier.length() <= 0) {
            JSONObject modifier = new JSONObject();
            modifier.putOpt("type", "");
            modifier.putOpt("amount", 0);
            modifier.putOpt("ref_number", "");
            pmodifier.put(modifier);
        }

        invoice.put("payment_modifiers", pmodifier);

        JSONArray linesArray = new JSONArray();
        for (InvoiceData bills : this.getInvoices()) {
            for (Invoiceline bill : bills.getLines()) {
                JSONObject line = new JSONObject();
                line.put("item_code", bill.getItemCode());
                line.put("item_name", bill.getItemName());
                line.put("service_group", bill.getServicePoint());
                line.put("charge_date", bill.getChargeDate());
                line.put("unit_price", bill.getUnitPrice());
                line.put("quantity", bill.getQuantity());
                line.put("amount", bill.getAmount());
                line.put("gross_amount", bill.getAmount());
                line.put("pre_authorization_code", "");

                JSONArray pf = new JSONArray();
                JSONObject p = new JSONObject();
                p.put("ref_number", "");
                pf.put(p);
                line.put("payment_reference", pf);
                linesArray.put(line);
            }
        }
        invoice.put("lines", linesArray);

        invoices.put(invoice);

        claim.put("invoices", invoices);

        return claim.toString();
    }


    @Override
    public String toString() {
        return "Claims{" + "id=" + id + ", invoiceNo=" + invoiceNo + ", referenceNumber=" + referenceNumber + ", payerCode=" + payerCode + ", payerName=" + payerName + ", patientName=" + patientName + ", patientNumber=" + patientNumber + ", memberNumber=" + memberNumber + ", serviceType=" + serviceType + ", locationCode=" + locationCode + ", locationName=" + locationName + ", schemeCode=" + schemeCode + ", schemeName=" + schemeName + ", visitNumber=" + visitNumber + ", visitStart=" + visitStart + ", visitEnd=" + visitEnd + ", ICD10Codes=" + ICD10Codes + ", invoices=" + invoices + '}';
    }

}
