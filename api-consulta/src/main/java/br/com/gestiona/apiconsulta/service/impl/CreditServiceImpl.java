package br.com.gestiona.apiconsulta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestiona.apiconsulta.aop.annotation.QueryAudit;
import br.com.gestiona.apiconsulta.controller.dto.CreditDTO;
import br.com.gestiona.apiconsulta.repo.CreditRepo;
import br.com.gestiona.apiconsulta.service.ICreditService;
import br.com.gestiona.apiconsulta.util.CreditMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements ICreditService {

    private final CreditRepo repo;
    private final CreditMapper mapper;

    @Override
    @QueryAudit(type = "NFS-e")
    public List<CreditDTO> listCreditsByNfse(String nfse) {
        return repo.findByNumeroNfse(nfse).stream().map(mapper::toDTO).toList();
    }

    @Override
    @QueryAudit(type = "Numero do Crédito")
    public CreditDTO findByCreditNumber(String creditNumber) {
        return mapper.toDTO(repo.findByNumeroCredito(creditNumber));
    }
    
}
