package com.pt.cuentas.database.repository;

import com.pt.cuentas.database.models.ClienteReferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteReferenciaRepository extends JpaRepository<ClienteReferencia, UUID> {
}
