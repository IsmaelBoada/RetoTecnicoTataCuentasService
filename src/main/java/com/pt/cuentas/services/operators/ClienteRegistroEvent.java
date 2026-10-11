package com.pt.cuentas.services.operators;

import com.pt.cuentas.config.RabbitMQConfig;
import com.pt.cuentas.database.models.ClienteReferencia;
import com.pt.cuentas.database.repository.ClienteReferenciaRepository;
import com.pt.cuentas.rest.events.ClienteRegistradoEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteRegistroEvent {


    private final ClienteReferenciaRepository clienteReferenciaRepository;

    @RabbitListener(queues = RabbitMQConfig.CLIENTE_REGISTRADO)
    @Transactional
    public void recibir(ClienteRegistradoEvent evento) {

        ClienteReferencia cliente = clienteReferenciaRepository
                .findById(evento.tokenId())
                .orElseGet(ClienteReferencia::new);

        cliente.setTokenId(evento.tokenId());
        cliente.setNombre(evento.nombre());
        cliente.setIdentificacion(evento.identificacion());

        clienteReferenciaRepository.save(cliente);

        log.info("Referencia local de cliente guardada: {}",
                evento.tokenId());
    }
}


