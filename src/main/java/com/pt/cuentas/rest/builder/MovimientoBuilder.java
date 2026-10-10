package com.pt.cuentas.rest.builder;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.database.models.Movimiento;
import com.pt.cuentas.rest.models.MovimientoDto;
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
                .tokenIdCliente(model.getTokenIdCliente())
                .cuenta(cuenta)
                .build();
    }


    public Movimiento builderUpdate(Movimiento item,
                                    MovimientoDto model, Cuenta cuenta) {
        return Movimiento.builder()
                .id(item.getId())
                .tokenId(item.getTokenId())
                .tipoMovimiento(model.getTipoMovimiento())
                .valor(model.getValor())
                .saldo(model.getSaldo())
                .fecha(Objects.nonNull(model.getFecha())
                        ? DateUtils.toLocalDate(model.getFecha())
                        : LocalDate.now())
                .tokenIdCliente(model.getTokenIdCliente())
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
                .tokenIdCliente(model.getTokenIdCliente())
                .cuenta(cuentaBuilder.builderResponse(model.getCuenta()))
                .build();
    }

}
