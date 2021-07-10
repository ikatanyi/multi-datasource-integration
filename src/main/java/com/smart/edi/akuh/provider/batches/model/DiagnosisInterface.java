package com.smart.edi.akuh.provider.batches.model;

import java.time.LocalDate;

public interface DiagnosisInterface {
    public String getReceiptNo();
    public LocalDate getBillDate();
    public  LocalDate getAppointmentDate();
    public LocalDate getCodingDate();
    public String getApptNo();
    public String getPin();
    public String getIcdCode();
    public String getDiagType();
    public String getDescription();
}
