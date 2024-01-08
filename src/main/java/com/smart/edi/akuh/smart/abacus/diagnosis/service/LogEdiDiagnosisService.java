package com.smart.edi.akuh.smart.abacus.diagnosis.service;

import com.smart.edi.akuh.provider.diagnosis.service.DiagnosisService;
import com.smart.edi.akuh.smart.abacus.claims.model.StgSlinkClaim;
import com.smart.edi.akuh.smart.abacus.claims.repository.StgSlinkClaimRepository;
import com.smart.edi.akuh.smart.abacus.claims.service.AbacusService;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import com.smart.edi.akuh.smart.abacus.diagnosis.repository.LogEdiDiagnosisRepository;
import com.smart.edi.akuh.util.ListProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogEdiDiagnosisService {
    private final LogEdiDiagnosisRepository logEdiDiagnosisRepository;
    private final DiagnosisService diagnosisService;
    private final StgSlinkClaimRepository stgSlinkClaimRepository;
    private final AbacusService abacusService;

    @Async
    @Scheduled(fixedDelay = 50000)
    public void claimCleanUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        List<String> invoices = stgSlinkClaimRepository.findPendingInvoices("SKSPKDN_4", 1);

        if (invoices.isEmpty() || invoices == null)
            return;

        int i=0;
        List<List<String>> parts = ListProcessor.chopped(invoices, 500);
        for(List<String> part:parts) {
            List<LogEdiDiagnosis> logEdiDiagnosis = diagnosisService.fetchDiagnosis(part);
            for (String invoiceNo : part) {
                List<LogEdiDiagnosis> result = logEdiDiagnosis.stream()
                        .filter(a -> Objects.equals(a.getReceiptNo(), invoiceNo))
                        .map(x -> {
                            LogEdiDiagnosis diagnosis = null;
                            Optional<LogEdiDiagnosis> logEdiDiag = isExisting(x.getReceiptNo(), x.getIcdCode(), "SKSPKDN_4", x.getBillDate());
                            if(logEdiDiag.isPresent())
                                diagnosis =  logEdiDiag.get();
                            else {
                                x.setProviderKey("SKSPKDN_4");
                                x.setViewSource("BACKEND_PROVIDER");
                                x.setIsTransmitted(0);
                                x.setApptNo(x.getApptNo() != null ? x.getApptNo() : "n/a");
                                x.setPin(x.getPin() != null ? x.getPin() : "n/a");
                                diagnosis =  logEdiDiagnosisRepository.save(x);
                            }
                            return diagnosis;
                        })
                        .collect(Collectors.toList());
                StgSlinkClaim stgSlinkClaim = stgSlinkClaimRepository.findByInvoiceNr(invoiceNo, 1);
                if (result == null || result.isEmpty()) {
                    stgSlinkClaim.setBackendProviderFetchDate(LocalDateTime.now());
                    stgSlinkClaim.setBackendProviderStatusMsg("Diagnosis still missing");
                } else {
                    stgSlinkClaim.setBackendProviderStatus(3);
                    stgSlinkClaim.setBackendProviderFetchDate(LocalDateTime.now());
                    stgSlinkClaim.setBackendProviderStatusMsg("Claim and Diagnosis retrieved successfully");
                }
                abacusService.saveStgClaim(stgSlinkClaim);
            }
        }
    }




    public Optional<LogEdiDiagnosis>  isExisting(String receiptNo, String icdCode, String provider, LocalDate billDate) {
        return logEdiDiagnosisRepository.findDiagnosisByReceipNoAndProviderKey(receiptNo, icdCode, provider, billDate);
    }

    @Transactional
    public List<LogEdiDiagnosis> saveAll(List<LogEdiDiagnosis> logEdiDiagnosis){
        return logEdiDiagnosisRepository.saveAll(logEdiDiagnosis);
    }

}
