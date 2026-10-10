package com.pt.cuentas.database.repository;

import com.pt.cuentas.database.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByTokenId(UUID tokenId);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
