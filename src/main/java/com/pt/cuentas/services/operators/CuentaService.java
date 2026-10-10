package com.pt.cuentas.services.operators;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.database.repository.CuentaRepository;
import com.pt.cuentas.rest.builder.CuentaBuilder;
import com.pt.cuentas.rest.models.CuentaDto;
import com.pt.cuentas.services.exception.GeneralException;
import com.pt.cuentas.services.messages.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CuentaService {

    private final CuentaBuilder cuentaBuilder;
    private final CuentaRepository cuentaRepository;


    public CuentaDto save(CuentaDto model) {
        return cuentaBuilder.builderResponse(cuentaRepository.save(cuentaBuilder.builder(model)));
    }

    public CuentaDto update(UUID tokenId, CuentaDto model) {

        Cuenta cuenta = cuentaRepository.findByTokenId(tokenId).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId)));
        return cuentaBuilder.builderResponse(cuentaRepository.save(cuentaBuilder.builderUpdate(cuenta, model)));
    }

    public CuentaDto findByIdToken(UUID tokenId) {
        return cuentaBuilder.builderResponse(cuentaRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId))));
    }


    public void delete(UUID tokenId) {
        Cuenta cuenta = cuentaRepository.findByTokenId(tokenId).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId)));
        cuentaRepository.delete(cuenta);
    }
}
