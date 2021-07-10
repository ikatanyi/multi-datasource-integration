package com.smart.edi.akuh.provider.claims.service;

import com.smart.edi.akuh.provider.claims.data.DiagnosisData;
import com.smart.edi.akuh.provider.claims.model.Copay;
import com.smart.edi.akuh.provider.claims.model.Claim;
import com.smart.edi.akuh.provider.claims.model.ClaimRequest;
import com.smart.edi.akuh.provider.claims.data.InvoiceData;
import com.smart.edi.akuh.provider.claims.model.DiagnosisInterface;
import com.smart.edi.akuh.provider.claims.model.Invoiceline;
import com.smart.edi.akuh.provider.claims.model.InvoicelineInterface;
import com.smart.edi.akuh.provider.claims.repository.ClaimRepository;
import com.smart.edi.akuh.provider.claims.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ClaimRepository claimRepository;
    private final InvoiceRepository invoiceRepository;

    public List<ClaimRequest> getClaimsToSwitch(List<String>invoices) {
        List<Claim> claims = claimRepository.findClaims(invoices);
        return claims.stream()
                .map(x -> {
                    ClaimRequest claimRequest = x.toClaimRequest();
                    claimRequest.setDiagnosis(getDiagnosis (x.getBillNo()));
                    claimRequest.getInvoices().add(findInvoices(x.getBillNo()));
                    return claimRequest;
                })
                .collect(Collectors.toList());
    }

    private InvoiceData findInvoices(String invoiceNo) {
            Invoiceline invoiceLine;
            Copay copay;
            List<InvoicelineInterface> lines = invoiceRepository.findInvoices(invoiceNo);
            List<Copay> copays = new ArrayList<>();
            List<Invoiceline>invoiceLines = new ArrayList<>();
            InvoiceData invoice = new InvoiceData(invoiceNo);            
            for(InvoicelineInterface line: lines){
                invoiceLine = new Invoiceline();
                invoiceLine.setAmount(line.getTotalSpnrAmt());
                invoiceLine.setUnitPrice(line.getSpnrAmt());
                invoiceLine.setChargeDate(line.getCtdChrgDt());
                invoiceLine.setItemCode(line.getCtdItemCd());
                invoiceLine.setItemName(line.getCtdItemDes().trim());
                invoiceLine.setQuantity(line.getQty());
                invoiceLine.setServicePoint(line.getItemGroup());
                invoiceLines.add(invoiceLine);                
                copay = new Copay();
                copay.setCharge(line.getTotalPatAmt()!=null?line.getTotalPatAmt():0.0);
                copays.add(copay);
            }        
            invoice.setCopays(((ArrayList<Copay>) copays ));
            invoice.setLines((ArrayList<Invoiceline>) invoiceLines);
            return invoice;
    }
    
    private List<DiagnosisData> getDiagnosis (String invoiceNo){
        return claimRepository.findDiagnosis(invoiceNo).stream()
                    .map(x->{
                        DiagnosisData data = new DiagnosisData();
                        data.setCode(x.getIcdCode());
                        data.setIsPrimary(x.getIsPrimary());
                        data.setName(x.getDescription());
                        return data;                                
                    })
                    .collect(Collectors.toList());
    }

}
