package br.com.gestiona.apiconsulta.aop;

import java.net.InetAddress;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestiona.apiconsulta.integration.kafka.IAuditoriaProducer;
import br.com.gestiona.apiconsulta.integration.kafka.dto.AuditEvent;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final IAuditoriaProducer auditoriaProducer;
    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Around("@annotation(br.com.gestiona.apiconsulta.aop.annotation.QueryAudit)")
    public Object interceptarConsultas(ProceedingJoinPoint joinPoint) throws Throwable {
        
        long initial = 0;
        long end = 0;
        Object result = null;
        try {
            initial = System.currentTimeMillis();
            result = joinPoint.proceed();
            end = System.currentTimeMillis();
            
            // Criando evento de auditoria
            var evento = new AuditEvent<>(
                    getUser(),
                    getHostname(),
                    "Consulta Crédito",
                    "SUCESSO",
                    "Requisição interceptada via AOP",
                    end - initial,
                    result
            );

            auditoriaProducer.enviarEvento(objectMapper.writeValueAsString(evento));

            return result;

        } catch(Exception e) {

            var evento = new AuditEvent<>(
                    getUser(),
                    getHostname(),
                    "Consulta Crédito",
                    "ERRO",
                    e.getMessage(),
                    end - initial,
                    result
            );
            auditoriaProducer.enviarEvento(objectMapper.writeValueAsString(evento));
            throw e;
        }
        
    }

    private String getHostname() {
        return environment.getProperty("COMPUTERNAME", 
                environment.getProperty("HOSTNAME", getDns()));
    }

    private String getDns() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "desconhecido";
        }
    }

    private String getUser() {
        return environment.getProperty("user.name");
    }
    
}
