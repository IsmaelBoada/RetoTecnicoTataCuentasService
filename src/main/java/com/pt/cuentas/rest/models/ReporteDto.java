package com.pt.cuentas.rest.models;

import com.pt.cuentas.services.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDto {

    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal valorMovimiento;
    private BigDecimal saldoDisponible;

}
