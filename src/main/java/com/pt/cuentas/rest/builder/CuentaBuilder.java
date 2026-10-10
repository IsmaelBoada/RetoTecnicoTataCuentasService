package com.pt.cuentas.rest.builder;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.rest.models.CuentaDto;
import org.springframework.stereotype.Component;

@Component
public class CuentaBuilder {


    public Cuenta builder(CuentaDto model) {
        return Cuenta.builder()
                .numeroCuenta(model.getNumeroCuenta())
                .tipoCuenta(model.getTipoCuenta())
                .saldoInicial(model.getSaldoInicial())
                .estado(model.getEstado())
                .build();
    }


    public Cuenta builderUpdate(Cuenta item, CuentaDto model) {
        return Cuenta.builder()
                .id(item.getId())
                .tokenId(item.getTokenId())
                .numeroCuenta(model.getNumeroCuenta())
                .tipoCuenta(model.getTipoCuenta())
                .saldoInicial(model.getSaldoInicial())
                .estado(model.getEstado())
                .build();
    }


    public CuentaDto builderResponse(Cuenta model) {
        return CuentaDto.builder()
                .id(model.getId())
                .tokenId(model.getTokenId())
                .numeroCuenta(model.getNumeroCuenta())
                .tipoCuenta(model.getTipoCuenta())
                .saldoInicial(model.getSaldoInicial())
                .estado(model.getEstado())
                .build();
    }

}
