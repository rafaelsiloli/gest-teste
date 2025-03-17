package br.com.gestiona.apiconsulta.unit.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gestiona.apiconsulta.controller.dto.CreditDTO;
import br.com.gestiona.apiconsulta.entity.Credit;
import br.com.gestiona.apiconsulta.repo.CreditRepo;
import br.com.gestiona.apiconsulta.service.impl.CreditServiceImpl;
import br.com.gestiona.apiconsulta.util.CreditMapper;

class CreditServiceImplTest {

    @Mock
    private CreditRepo creditRepo;

    @Mock
    private CreditMapper creditMapper;

    @InjectMocks
    private CreditServiceImpl creditService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfCreditsWhenSearchingByNfse() {
        String defaultValue = "Teste123";
        Credit credit = new Credit();
        credit.setNumeroNfse(defaultValue);

        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setNumeroNfse(defaultValue);

        when(creditRepo.findByNumeroNfse(defaultValue)).thenReturn(List.of(credit));
        when(creditMapper.toDTO(credit)).thenReturn(creditDTO);

        List<CreditDTO> result = creditService.listCreditsByNfse(defaultValue);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(defaultValue, result.get(0).getNumeroNfse());

        verify(creditRepo, times(1)).findByNumeroNfse(defaultValue);
        verify(creditMapper, times(1)).toDTO(credit);
    }

    @Test
    void shouldReturnCreditWhenSearchingByCreditNumber() {
        String defaultValue = "Teste123";
        Credit credit = new Credit();
        credit.setNumeroCredito(defaultValue);

        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setNumeroCredito(defaultValue);

        when(creditRepo.findByNumeroCredito(defaultValue)).thenReturn(credit);
        when(creditMapper.toDTO(credit)).thenReturn(creditDTO);

        CreditDTO result = creditService.findByCreditNumber(defaultValue);

        assertNotNull(result);
        assertEquals(defaultValue, result.getNumeroCredito());

        verify(creditRepo, times(1)).findByNumeroCredito(defaultValue);
        verify(creditMapper, times(1)).toDTO(credit);
    }

    @Test
    void shouldHandleNullResultFromRepository() {
        String defaultValue = "Teste123";
        when(creditRepo.findByNumeroCredito(defaultValue)).thenReturn(null);

        CreditDTO result = creditService.findByCreditNumber(defaultValue);

        assertNull(result);

        verify(creditRepo, times(1)).findByNumeroCredito(defaultValue);

        verify(creditMapper, times(1)).toDTO(any());
    }
}
