package br.edu.pucgoias.universidade.api.persistencia;

import br.edu.pucgoias.universidade.api.entidade.Universidade;
import org.springframework.data.repository.CrudRepository;

public interface UniversidadeRepository extends CrudRepository<Universidade, Integer> {
}
