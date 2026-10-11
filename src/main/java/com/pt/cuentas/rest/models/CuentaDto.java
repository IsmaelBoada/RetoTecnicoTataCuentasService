package com.pt.cuentas.rest.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {

    private Long id;
    private UUID tokenId;
    @NotNull(message = "El número de cuenta no puede ser nulo")
    private String numeroCuenta;
    private String tipoCuenta;
    @NotNull(message = "El saldo inicial no puede ser nulo")
    private BigDecimal saldoInicial;
    private Boolean estado;
    @NotNull(message = "El token del cliente no puede ser nulo")
    private UUID tokenIdCliente;

}
