package com.pt.cuentas.rest.models;

import com.pt.cuentas.services.utils.DateUtils;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@ToString
public class FilterModelDto {


    private String fechaInicial;
    private String fechaFinal;
    private UUID idTokenCliente;


    public LocalDate getFechaInicial() {
        if (fechaInicial == null) return null;
        return DateUtils.toLocalDate(fechaInicial);
    }

    public LocalDate getFechaFinal() {
        if (fechaFinal == null) return null;
        return DateUtils.toLocalDate(fechaFinal);
    }
}
