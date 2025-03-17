package br.com.gestiona.apiconsulta.unit.aop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestiona.apiconsulta.aop.AuditAspect;
import br.com.gestiona.apiconsulta.integration.kafka.IAuditoriaProducer;
import br.com.gestiona.apiconsulta.integration.kafka.dto.AuditEvent;

class AuditAspectTest {

    @Mock
    private IAuditoriaProducer auditoriaProducer;

    @Mock
    private Environment environment;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @InjectMocks
    private AuditAspect auditAspect;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInterceptAndAuditSuccessfully() throws Throwable {
        // Mockando um resultado para a execução do método
        Object expectedResult = "Mocked Result";
        when(joinPoint.proceed()).thenReturn(expectedResult);

        // Mockando a serialização do objeto
        when(objectMapper.writeValueAsString(any(AuditEvent.class))).thenReturn("mockedEvent");

        // Executando o aspecto
        Object result = auditAspect.interceptarConsultas(joinPoint);

        // Verificações
        assertNotNull(result);
        assertEquals(expectedResult, result);
        verify(auditoriaProducer, times(1)).enviarEvento("mockedEvent");
    }

    @Test
    void shouldInterceptAndAuditError() throws Throwable {
        // Simulando uma exceção no método interceptado
        RuntimeException simulatedException = new RuntimeException("Erro simulado");
        when(joinPoint.proceed()).thenThrow(simulatedException);

        // Mockando a serialização do evento de erro
        when(objectMapper.writeValueAsString(any(AuditEvent.class))).thenReturn("mockedErrorEvent");

        // Verifica se a exceção é lançada corretamente
        Exception thrownException = assertThrows(RuntimeException.class, () -> {
            auditAspect.interceptarConsultas(joinPoint);
        });

        // Valida se a exceção lançada é a esperada
        assertEquals("Erro simulado", thrownException.getMessage());

        // Verifica se a auditoria do erro foi chamada
        verify(auditoriaProducer, times(1)).enviarEvento("mockedErrorEvent");
    }

}
