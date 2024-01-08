package com.smart.edi.akuh.provider.diagnosis.service;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.provider.diagnosis.repository.DiagnosisRepository;
import com.smart.edi.akuh.smart.abacus.diagnosis.model.LogEdiDiagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public List<LogEdiDiagnosis> fetchDiagnosis(List invoices){
        return diagnosisRepository.getDiagnosis(invoices)
                .stream()
                .map(x->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'yyyyMMDD'");
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setAppointmentDate(x.getAppointmentDate());
                    diagnosis.setApptNo(x.getApptNo());
                    diagnosis.setDiagType(x.getDiagType());
                    diagnosis.setDescription(x.getDescription());
                    diagnosis.setBillDate(x.getBillDate());
                    diagnosis.setCodingDate(x.getCodingDate());
                    diagnosis.setIcdCode(x.getIcdCode());
                    diagnosis.setPin(x.getPin());
                    diagnosis.setReceiptNo(x.getReceiptNo());
                    return diagnosis.toLogEdiDiagnosis();
//                    String billDate = formatter.format(x.getBillDate());
//                    if (x.getReceiptNo() == null || "".equals(x.getReceiptNo()))
//                        diagnosis.setReceiptNo(x.getIcdCode() + "." + "SKSP_183" +"."+ x.getPin() + "."+billDate);
//                    return diagnosis;
                })
                .collect(Collectors.toList());
    }
}
