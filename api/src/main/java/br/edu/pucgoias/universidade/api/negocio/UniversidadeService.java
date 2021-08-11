package br.edu.pucgoias.universidade.api.negocio;

import br.edu.pucgoias.universidade.api.entidade.Universidade;

import java.util.List;

public interface UniversidadeService {

    public Universidade incluir (Universidade universidade);
    public List<Universidade> listar();
    public Universidade consultar(Integer id);
    public void alterar(Universidade universidade);
    public void excluir(Integer id);

}
