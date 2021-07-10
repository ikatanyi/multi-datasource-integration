package com.smart.edi.akuh.provider.claims.model;

import java.time.LocalDate;

public interface InvoicelineInterface {
    public Double getTotalSpnrAmt();
    public Double getSpnrAmt();
    public String getCtdChrgDt();
    public String getCtdItemCd();
    public String getCtdItemDes();
    public Double getQty();
    public String getItemGroup();
    public Double getTotalPatAmt();
}
