package com.pt.cuentas.services.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoMovimiento {

    E("Egreso"),
    I("Ingreso");

    private final String descripcion;

}
