package com.smart.edi.akuh.smart.abacus.diagnosis.service;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.provider.diagnosis.service.DiagnosisService;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import com.smart.edi.akuh.smart.abacus.diagnosis.repository.LogEdiDiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogEdiDiagnosisService {
    private final LogEdiDiagnosisRepository logEdiDiagnosisRepository;
    private final DiagnosisService diagnosisService;

    public void saveDiagnosis(){
        List<LogEdiDiagnosis> logEdiDiagnosis = diagnosisService.fetchDiagnosis().stream()
                .filter(x-> isExisting(x.getReceiptNo(), x.getIcdCode(), x.getBillDate()))
                .map(x->x.toLogEdiDiagnosis())
                .collect(Collectors.toList());
        saveAll(logEdiDiagnosis);

        logEdiDiagnosis = diagnosisService.fetchSateliteDiagnosis().stream()
                .filter(x-> isExisting(x.getReceiptNo(),x.getIcdCode(), x.getBillDate()))
                .map(x->x.toLogEdiDiagnosis())
                .collect(Collectors.toList());
        saveAll(logEdiDiagnosis);
    }




    public boolean isExisting(String receiptNo, String icdCode, LocalDate billDate) {
        Optional<Diagnosis> diagnosis = logEdiDiagnosisRepository.findDiagnosisByReceipNoAndProviderKey(receiptNo,icdCode,billDate);
       return diagnosis.isPresent();
    }

    @Transactional
    public List<LogEdiDiagnosis> saveAll(List<LogEdiDiagnosis> logEdiDiagnosis){
        return logEdiDiagnosisRepository.saveAll(logEdiDiagnosis);
    }

}
