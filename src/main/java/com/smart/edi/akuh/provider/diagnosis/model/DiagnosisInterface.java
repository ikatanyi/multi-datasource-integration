package com.smart.edi.akuh.provider.diagnosis.model;

import java.time.LocalDate;

public interface DiagnosisInterface {
    public String getReceiptNo();
    public String getBillDate();
    public  String getAppointmentDate();
    public String getCodingDate();
    public String getApptNo();
    public String getPin();
    public String getIcdCode();
    public String getDiagType();
    public String getDescription();
}
