package br.com.gestiona.apiconsulta.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "credito")
@Table(schema = "creditos", name = "credito")
public class Credit implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCredito;
	private String numeroNfse;
	private LocalDate dataConstituicao;
	private BigDecimal valorIssqn;
	private String tipoCredito;
	private Boolean simplesNacional;
	private BigDecimal aliquota;
	private BigDecimal valorFaturado;
	private BigDecimal valorDeducao;
	private BigDecimal baseCalculo;

}
