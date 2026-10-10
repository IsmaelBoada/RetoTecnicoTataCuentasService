package com.pt.cuentas.services.messages;


public abstract class ExceptionMessages {


    public ExceptionMessages() {
    }

    public static final String CUENTA_NOT_FOUND = "La cuenta con token id {0} no encontrada ";
    public static final String CUENTA_EXIST = "El número de cuenta {0} ya se encuentra registrado";

    public static final String MOVIMIENTO_NOT_FOUND = "El movimiento con token id {0} no encontrado ";
    public static final String ERROR_VALIDACIONES_REQUEST = "Existe los siguientes errores: ";

    public static final String ERROR_TIPO_ENUM = "Tipo movimiento no disponible:";
}
