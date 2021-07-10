package com.smart.edi.akuh.provider.batches.service;

import com.smart.edi.akuh.provider.batches.model.Batch;
import com.smart.edi.akuh.provider.batches.repository.BatchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {
    private final BatchesRepository batchesRepository;

    public List<Batch> fetchBatches(){
        return batchesRepository.findBatchesByDate();
    }
}
