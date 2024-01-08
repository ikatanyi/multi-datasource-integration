package com.smart.edi.akuh.smart.abacus.claims.service;

import com.smart.edi.akuh.provider.claims.model.ClaimRequest;
import com.smart.edi.akuh.provider.claims.service.ProviderService;
import com.smart.edi.akuh.smart.abacus.claims.model.LogEdiClaimsDelivery;
import com.smart.edi.akuh.smart.abacus.claims.model.StgSlinkClaim;
import com.smart.edi.akuh.smart.abacus.claims.repository.LogEdiClaimsDeliveryRepository;
import com.smart.edi.akuh.smart.abacus.claims.repository.StgSlinkClaimRepository;
import com.smart.edi.akuh.util.ListProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbacusService {
    private final LogEdiClaimsDeliveryRepository logEdiClaimsDeliveryRepository;
    private final StgSlinkClaimRepository stgSlinkClaimRepository;
    private final ProviderService providerService;

    @Scheduled(fixedDelay = 5000)
    public void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        List<String> invoices = stgSlinkClaimRepository.findPendingInvoices("SKSPKDN_4", 0);

        if (invoices.isEmpty() || invoices == null)
            return;

        List<List<String>> parts = ListProcessor.chopped(invoices, 1000);
        for (List<String> part : parts) {
            List<ClaimRequest> claimrequests = providerService.getClaimsToSwitch(part);
            for (String invoiceNo : part) {
                List<LogEdiClaimsDelivery> result = claimrequests.stream()
                        .filter(a -> Objects.equals(a.getInvoiceNo(), invoiceNo))
                        .map(x -> {
                            LogEdiClaimsDelivery logEdiClaimsDelivery = new LogEdiClaimsDelivery();
                            Optional<LogEdiClaimsDelivery> ediClaim2 = getEdiClaim(x.getInvoiceNo(), "SKSPKDN_4");
                            if (ediClaim2.isPresent()) {
                                logEdiClaimsDelivery = ediClaim2.get();
                            } else {
                                LocalDate localDate = LocalDate.parse(x.getInvoiceDate() != null ? x.getInvoiceDate() : LocalDate.now().toString());
                                logEdiClaimsDelivery.setClaimDate(localDate);
                                logEdiClaimsDelivery.setInvoiceNr(x.getInvoiceNo());
                                logEdiClaimsDelivery.setJsonString(x.claimToJson());
                                logEdiClaimsDelivery.setProviderKey("SKSPKDN_4");
                            }
                            LogEdiClaimsDelivery ediClaim = saveEdiClaim(logEdiClaimsDelivery);
                            return ediClaim;
                        })
                        .collect(Collectors.toList());
                StgSlinkClaim stgSlinkClaim = findStgClaimByInvoiceAndProvierKey(invoiceNo, 0);
                if (result == null || result.isEmpty()) {
                    stgSlinkClaim.setBackendProviderStatus(2);
                    stgSlinkClaim.setBackendProviderFetchDate(LocalDateTime.now());
                    stgSlinkClaim.setBackendProviderStatusMsg("not found on provider server");
                } else {
                    stgSlinkClaim.setBackendProviderStatus(1);
                    stgSlinkClaim.setBackendProviderFetchDate(LocalDateTime.now());
                    stgSlinkClaim.setBackendProviderStatusMsg("Claim Retrieved successfully");
                }
                saveStgClaim(stgSlinkClaim);
            }
        }
    }


    @Scheduled(fixedDelay = 1800000)
    public void claimCleanUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        List<String> invoices = stgSlinkClaimRepository.findPendingInvoices("SKSPKDN_4", 2);
        if (invoices.isEmpty() || invoices == null)
            return;
        List<List<String>> parts = ListProcessor.chopped(invoices, 1000);
        for (List<String> part : parts) {
            List<ClaimRequest> claimrequests = providerService.getClaimsToSwitch(part);
            for (String invoiceNo : part) {
                List<LogEdiClaimsDelivery> result = claimrequests.stream()
                        .filter(a -> Objects.equals(a.getInvoiceNo(), invoiceNo))
                        .map(x -> {
                            LogEdiClaimsDelivery logEdiClaimsDelivery = new LogEdiClaimsDelivery();
                            Optional<LogEdiClaimsDelivery> ediClaim2 = getEdiClaim(x.getInvoiceNo(), "SKSPKDN_4");
                            if (ediClaim2.isPresent()) {
                                logEdiClaimsDelivery = ediClaim2.get();
                            } else {
                                LocalDate localDate = LocalDate.parse(x.getInvoiceDate() != null ? x.getInvoiceDate() : LocalDate.now().toString());
                                logEdiClaimsDelivery.setClaimDate(localDate);
                                logEdiClaimsDelivery.setInvoiceNr(x.getInvoiceNo());
                                logEdiClaimsDelivery.setJsonString(x.claimToJson());
                                logEdiClaimsDelivery.setProviderKey("SKSPKDN_4");
                            }
                            LogEdiClaimsDelivery ediClaim = saveEdiClaim(logEdiClaimsDelivery);
                            return ediClaim;
                        })
                        .collect(Collectors.toList());
                StgSlinkClaim stgSlinkClaim = findStgClaimByInvoiceAndProvierKey(invoiceNo, 2);
                if (result == null || result.isEmpty()) {
                    stgSlinkClaim.setBackendProviderStatus(2);
                    stgSlinkClaim.setBackendProviderStatusMsg("not found on provider server");
                } else {
                    stgSlinkClaim.setBackendProviderStatus(1);
                    stgSlinkClaim.setBackendProviderStatusMsg("Claim Retrieved successfully");
                }
                saveStgClaim(stgSlinkClaim);
            }
        }
    }

    public Optional<LogEdiClaimsDelivery> getEdiClaim(String invoiceNo, String providerKey) {
        return logEdiClaimsDeliveryRepository.findByInvoiceNrAndProviderKey(invoiceNo, providerKey);
    }

    @Transactional
    public LogEdiClaimsDelivery saveEdiClaim(LogEdiClaimsDelivery logEdiClaimsDelivery) {
        return logEdiClaimsDeliveryRepository.save(logEdiClaimsDelivery);
    }

    public StgSlinkClaim findStgClaimByInvoiceAndProvierKey(String invoiceNo, Integer status) {
        return stgSlinkClaimRepository.findByInvoiceNr(invoiceNo, status);
    }

    @Transactional("abacusTransactionManager")
    public StgSlinkClaim saveStgClaim(StgSlinkClaim stgSlinkClaim) {
        return stgSlinkClaimRepository.save(stgSlinkClaim);
    }

}
