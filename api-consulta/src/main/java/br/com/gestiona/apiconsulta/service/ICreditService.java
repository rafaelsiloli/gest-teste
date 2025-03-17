package br.com.gestiona.apiconsulta.service;

import java.util.List;

import br.com.gestiona.apiconsulta.controller.dto.CreditDTO;

public interface ICreditService {
    List<CreditDTO> listCreditsByNfse(String nfse);
    CreditDTO findByCreditNumber(String creditNumber);
}
