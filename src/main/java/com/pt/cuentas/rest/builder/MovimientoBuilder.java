package com.pt.cuentas.rest.builder;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.database.models.Movimiento;
import com.pt.cuentas.database.projection.MovimientoReporteProjection;
import com.pt.cuentas.rest.models.MovimientoDto;
import com.pt.cuentas.rest.models.ReporteDto;
import com.pt.cuentas.services.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@AllArgsConstructor
public class MovimientoBuilder {


    private final CuentaBuilder cuentaBuilder;

    public Movimiento builder(MovimientoDto model, Cuenta cuenta) {
        return Movimiento.builder()
                .tipoMovimiento(model.getTipoMovimiento())
                .valor(model.getValor())
                .saldo(model.getSaldo())
                .fecha(Objects.nonNull(model.getFecha())
                        ? DateUtils.toLocalDate(model.getFecha())
                        : LocalDate.now())
                .cuenta(cuenta)
                .build();
    }


    public MovimientoDto builderResponse(Movimiento model) {
        return MovimientoDto.builder()
                .id(model.getId())
                .tokenId(model.getTokenId())
                .tipoMovimiento(model.getTipoMovimiento())
                .valor(model.getValor())
                .saldo(model.getSaldo())
                .fecha(DateUtils.toString(model.getFecha()))
                .cuenta(cuentaBuilder.builderResponse(model.getCuenta()))
                .build();
    }

    public ReporteDto builderReporte(MovimientoReporteProjection item) {
        return ReporteDto.builder()
                .fecha(DateUtils.toString(item.getFecha()))
                .cliente(item.getCliente())
                .numeroCuenta(item.getNumeroCuenta())
                .tipoCuenta(item.getTipoCuenta())
                .saldoInicial(item.getSaldoInicial())
                .estado(item.getEstado())
                .tipoMovimiento(item.getTipoMovimiento())
                .valorMovimiento(item.getValorMovimiento())
                .saldoDisponible(item.getSaldoDisponible())
                .build();
    }

}
