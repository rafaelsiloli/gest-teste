package br.com.gestiona.apiconsulta.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestiona.apiconsulta.controller.dto.CreditDTO;
import br.com.gestiona.apiconsulta.service.ICreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/creditos")
@Tag(name = "Créditos", description = "Endpoints para consulta de créditos")
@RequiredArgsConstructor
public class CreditController {

    private final ICreditService service;

    @GetMapping("/{numeroNfse}")
    @Operation(
        summary = "Buscar créditos por NFS-e",
        description = "Retorna uma lista de créditos associados a um número de NFS-e",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de créditos encontrada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CreditDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum crédito encontrado para o número de NFS-e informado")
        }
    )
    public ResponseEntity<List<CreditDTO>> buscarPorNfse(@PathVariable String numeroNfse) {
        List<CreditDTO> creditos = service.listCreditsByNfse(numeroNfse);

        if (creditos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditos);
    }

    @GetMapping("/credito/{numeroCredito}")
    @Operation(
        summary = "Buscar crédito por número de crédito",
        description = "Retorna os detalhes de um crédito específico pelo número do crédito",
        responses = {
            @ApiResponse(responseCode = "200", description = "Crédito encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CreditDTO.class))),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
        }
    )
    public ResponseEntity<CreditDTO> buscarPorCredito(@PathVariable String numeroCredito) {
        CreditDTO credit = service.findByCreditNumber(numeroCredito);

        if (credit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(credit);
    }

}
