package com.pt.cuentas.rest.models;

import com.pt.cuentas.services.enums.TipoMovimiento;
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
public class MovimientoDto {


    private Long id;
    private UUID tokenId;
    @NotNull(message = "El tipo de movimiento no puede ser nulo")
    private TipoMovimiento tipoMovimiento;
    @NotNull(message = "El valor no puede ser nulo")
    private BigDecimal valor;
    private BigDecimal saldo;
    private String fecha;
    private UUID tokenIdCliente;
    @NotNull(message = "El token de la cuenta no puede ser nulo")
    private UUID idTokenCuenta;

    private CuentaDto cuenta;
}
