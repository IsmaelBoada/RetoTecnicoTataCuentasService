package com.pt.cuentas.services.operators;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.database.models.Movimiento;
import com.pt.cuentas.database.repository.CuentaRepository;
import com.pt.cuentas.database.repository.MovimientoRepository;
import com.pt.cuentas.rest.builder.MovimientoBuilder;
import com.pt.cuentas.rest.models.MovimientoDto;
import com.pt.cuentas.services.exception.GeneralException;
import com.pt.cuentas.services.messages.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovimientoService {


    private final MovimientoBuilder movimientoBuilder;
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoDto save(MovimientoDto model) {
        return movimientoBuilder.builderResponse(movimientoRepository.save(movimientoBuilder.builder(model, getCuenta(model))));
    }


    public MovimientoDto update(UUID tokenId, MovimientoDto model) {

        Movimiento movimiento = movimientoRepository.findByTokenId(tokenId).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.MOVIMIENTO_NOT_FOUND, tokenId)));

        return movimientoBuilder.builderResponse(movimientoRepository
                .save(movimientoBuilder.builderUpdate(movimiento, model, getCuenta(model))));
    }

    public MovimientoDto findByIdToken(UUID tokenId) {
        return movimientoBuilder.builderResponse(movimientoRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new GeneralException(MessageFormat
                        .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId))));
    }


    public void delete(UUID tokenId) {
        Movimiento movimiento = movimientoRepository.findByTokenId(tokenId).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId)));
        movimientoRepository.delete(movimiento);
    }


    private Cuenta getCuenta(MovimientoDto model) {
        return cuentaRepository.findByTokenId(model.getIdTokenCuenta()).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, model.getIdTokenCuenta())));
    }


}
