package br.com.gestiona.apiconsulta.integration.kafka.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditEvent<T> {
    
    private String idEvent;
    private Instant timestamp;
    private String user;
    private String ipOrigin;
    private String typeQuery;
    private String result;
    private String menssage;
    private long durationMs;
    private T details;

    public AuditEvent(String user, String ipOrigin, String typeQuery, 
                           String result, String menssage, 
                           long durationMs, T details) {
        this.idEvent = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.user = user;
        this.ipOrigin = ipOrigin;
        this.typeQuery = typeQuery;
        this.result = result;
        this.menssage = menssage;
        this.durationMs = durationMs;
        this.details = details;
    }

}
