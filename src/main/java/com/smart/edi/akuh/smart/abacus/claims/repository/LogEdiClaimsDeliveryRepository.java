package com.smart.edi.akuh.smart.abacus.claims.repository;

import com.smart.edi.akuh.smart.abacus.claims.model.LogEdiClaimsDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LogEdiClaimsDeliveryRepository extends JpaRepository<LogEdiClaimsDelivery, Long> {
    Optional<LogEdiClaimsDelivery> findByInvoiceNrAndProviderKey(@Param("invoiceNr") String invoiceNr, @Param("providerKey") String providerKey);
}
