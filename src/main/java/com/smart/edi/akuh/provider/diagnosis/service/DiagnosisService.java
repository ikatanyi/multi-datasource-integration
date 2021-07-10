package com.smart.edi.akuh.provider.diagnosis.service;

import com.smart.edi.akuh.provider.diagnosis.model.Diagnosis;
import com.smart.edi.akuh.provider.diagnosis.repository.ProviderDiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final ProviderDiagnosisRepository diagnosisRepository;

    public List<Diagnosis> fetchDiagnosis(){
        return diagnosisRepository.findDiagnosisByDate()
                .stream()
                .map(x->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'yyyyMMDD'");
                    String billDate = formatter.format(x.getBillDate());
                    if (x.getReceiptNo() == null || "".equals(x.getReceiptNo()))
                        x.setReceiptNo(x.getIcdCode() + "." + "SKSP_183" +"."+ x.getPin() + "."+billDate);
                    return x;
                })
                .collect(Collectors.toList());
    }

    public List<Diagnosis> fetchSateliteDiagnosis(){
        return diagnosisRepository.getSateLiteDiagNosis()
                .stream()
                .map(x->{
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setAppointmentDate(x.getAppointmentDate());
                    diagnosis.setApptNo(x.getApptNo());
                    diagnosis.setDiagType(x.getDiagType());
                    diagnosis.setDescription(x.getDescription());
                    diagnosis.setBillDate(x.getBillDate());
                    diagnosis.setCodingDate(x.getCodingDate());
                    diagnosis.setIcdCode(x.getIcdCode());
                    diagnosis.setPin(x.getPin());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'yyyyMMDD'");
                    String billDate = formatter.format(x.getBillDate());
                    if (x.getReceiptNo() == null || "".equals(x.getReceiptNo()))
                        diagnosis.setReceiptNo(x.getIcdCode() + "." + "SKSP_183" +"."+ x.getPin() + "."+billDate);
                    return diagnosis;
                })
                .collect(Collectors.toList());
    }

    public Diagnosis saveDiagnosis(Diagnosis diagnosis){
        return diagnosisRepository.save(diagnosis);
    }

    /**
            * Get A list of diagnosis from AKUH staging table
     *
             * @return list of {@link com.smart.edi.akuh.model.Diagnosis
    }
     */

}
