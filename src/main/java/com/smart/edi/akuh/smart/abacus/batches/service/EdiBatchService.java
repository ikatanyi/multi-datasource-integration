package com.smart.edi.akuh.smart.abacus.batches.service;

import com.smart.edi.akuh.provider.batches.service.BatchService;
import com.smart.edi.akuh.smart.abacus.batches.model.LogEdiBatches;
import com.smart.edi.akuh.smart.abacus.batches.repository.LogEdiBatchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EdiBatchService {
    private final LogEdiBatchesRepository logEdiBatchesRepository;
    private final BatchService batchService;

    public void saveDiagnosis(){
        List<LogEdiBatches> logEdiBatches = batchService.fetchBatches().stream()
                .filter(x-> isExisting(x.getCahDisBatchNo()))
                .map(x->x.toLogEdiBatches())
                .collect(Collectors.toList());
        saveAll(logEdiBatches);
    }

    public boolean isExisting(String batchNumber) {
        Optional<LogEdiBatches> diagnosis = logEdiBatchesRepository.findByBatchNumber(batchNumber);
       return diagnosis.isPresent();
    }

    @Transactional
    public List<LogEdiBatches> saveAll(List<LogEdiBatches> logEdiBatches){
        return logEdiBatchesRepository.saveAll(logEdiBatches);
    }

}
