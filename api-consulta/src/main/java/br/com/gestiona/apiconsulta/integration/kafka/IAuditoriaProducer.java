package br.com.gestiona.apiconsulta.integration.kafka;

public interface IAuditoriaProducer {
    void enviarEvento(String evento);
}
