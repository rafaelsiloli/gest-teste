package br.com.gestiona.apiconsulta.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gestiona.apiconsulta.entity.Credit;

@Repository
public interface CreditRepo extends JpaRepository<Credit, Long>{
    
    void deleteById(Long id);

    List<Credit> findByNumeroNfse(@Param("numeroNfse") String numeroNfse);

    Credit findByNumeroCredito(@Param("numeroCredito") String numeroCredito);
    
}
