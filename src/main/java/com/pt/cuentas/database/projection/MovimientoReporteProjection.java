package com.pt.cuentas.database.projection;

import com.pt.cuentas.services.enums.TipoMovimiento;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovimientoReporteProjection {

    LocalDate getFecha();

    String getCliente();

    String getNumeroCuenta();

    String getTipoCuenta();

    BigDecimal getSaldoInicial();

    Boolean getEstado();

    TipoMovimiento getTipoMovimiento();

    BigDecimal getValorMovimiento();

    BigDecimal getSaldoDisponible();

}
