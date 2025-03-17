package br.com.gestiona.apiconsulta.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.gestiona.apiconsulta.controller.dto.CreditDTO;
import br.com.gestiona.apiconsulta.entity.Credit;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    
    @Mapping(target = "simplesNacional", expression = "java(credit.getSimplesNacional() ? \"Sim\" : \"Não\")")
    CreditDTO toDTO(Credit credit);

}
