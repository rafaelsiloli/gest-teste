package br.com.gestiona.apiconsulta.integration.kafka.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.gestiona.apiconsulta.integration.kafka.IAuditoriaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditProducerImpl implements IAuditoriaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEvento(String evento) {
        log.info(evento);
        kafkaTemplate.send("eventos-auditoria", evento);
    }

}
