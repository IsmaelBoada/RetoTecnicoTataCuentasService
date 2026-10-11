package com.pt.cuentas.services.operators;

import com.pt.cuentas.database.models.Cuenta;
import com.pt.cuentas.database.repository.CuentaRepository;
import com.pt.cuentas.database.repository.MovimientoRepository;
import com.pt.cuentas.rest.builder.MovimientoBuilder;
import com.pt.cuentas.rest.models.FilterModelDto;
import com.pt.cuentas.rest.models.MovimientoDto;
import com.pt.cuentas.rest.models.ReporteDto;
import com.pt.cuentas.services.enums.TipoMovimiento;
import com.pt.cuentas.services.exception.GeneralException;
import com.pt.cuentas.services.messages.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovimientoService {


    private final MovimientoBuilder movimientoBuilder;
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Transactional
    public MovimientoDto registroMovimiento(MovimientoDto model) {
        Cuenta cuenta = existCuenta(model);
        BigDecimal saldoActual = validarMovimiento(model, cuenta);
        model.setSaldo(saldoActual);
        return movimientoBuilder.builderResponse(movimientoRepository.save(movimientoBuilder.builder(model, cuenta)));
    }

    private BigDecimal validarMovimiento(MovimientoDto model, Cuenta cuenta) throws GeneralException {
        if (cuenta.getSaldoDisponible().compareTo(model.getValor()) < 0 &&
                model.getTipoMovimiento() == TipoMovimiento.RETIRO) {
            throw new GeneralException(MessageFormat.format(ExceptionMessages.SALDO_INSUFICIENTE,
                    cuenta.getNumeroCuenta()));
        }
        return actualizarSaldo(model, cuenta);
    }


    private BigDecimal actualizarSaldo(MovimientoDto model, Cuenta cuenta) {

        BigDecimal saldoCambio = model.getValor();

        if (model.getValor() == null ||
                model.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new GeneralException(ExceptionMessages.ERROR_VALOR_NEGATIVO);
        }

        if (model.getTipoMovimiento() == TipoMovimiento.RETIRO) {
            saldoCambio = saldoCambio.negate();
        }

        if (model.getTipoMovimiento() == TipoMovimiento.DEPOSITO) {
            if (saldoCambio.compareTo(BigDecimal.ZERO) <= 0) {
                throw new GeneralException(ExceptionMessages.VALOR_MENOR_CERO);
            }
        }

        BigDecimal saldoActual = cuenta.getSaldoDisponible().add(saldoCambio);

        cuenta.setSaldoDisponible(saldoActual);
        cuentaRepository.save(cuenta);

        return saldoActual;
    }


    public MovimientoDto findByIdToken(UUID tokenId) {
        return movimientoBuilder.builderResponse(movimientoRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new GeneralException(MessageFormat
                        .format(ExceptionMessages.CUENTA_NOT_FOUND, tokenId))));
    }


    private Cuenta existCuenta(MovimientoDto model) {
        return cuentaRepository.findByTokenId(model.getIdTokenCuenta()).orElseThrow(() -> new GeneralException(MessageFormat
                .format(ExceptionMessages.CUENTA_NOT_FOUND, model.getIdTokenCuenta())));
    }


    public Page<ReporteDto> obtenerReporte(FilterModelDto filter, Pageable pageable) {
        return movimientoRepository.buscarReporte(filter.getFechaInicial(),
                        filter.getFechaFinal(), filter.getIdTokenCliente(), pageable)
                .map(movimientoBuilder::builderReporte);
    }

}
