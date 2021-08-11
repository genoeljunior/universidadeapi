package br.edu.pucgoias.universidade.api.negocio;

import br.edu.pucgoias.universidade.api.entidade.Curso;
import br.edu.pucgoias.universidade.api.entidade.Universidade;
import br.edu.pucgoias.universidade.api.persistencia.UniversidadeRepository;
import br.edu.pucgoias.universidade.api.util.UniversidadeAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UniversidadeServiceImpl implements UniversidadeService{

    @Autowired
    UniversidadeRepository universidadeRepository;

    public UniversidadeRepository getUniversidadeRepository(){
        return universidadeRepository;
    }

    public void setUniversidadeRepository(UniversidadeRepository universidadeRepository){
        this.universidadeRepository=universidadeRepository;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Universidade incluir(Universidade universidade) {
        //Regra de negocio, incluir universidade autorizada.
        if (universidade.getAutorizadaMec()){
            for (Curso curso:universidade.getListaCursos()){
                curso.setUniversidade(universidade);
            }
            this.getUniversidadeRepository().save(universidade);
        } else {
            throw new UniversidadeAPIException("Para ser incluída, a Universidade precisa ser autorizada");
        }
        return universidade;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Universidade> listar() {
        try {
            List<Universidade> universidades = (List<Universidade>) this.getUniversidadeRepository().findAll();
            return universidades;
        } catch (Exception ex){
            throw new UniversidadeAPIException("Não foi possivel listar as Universidades");
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public Universidade consultar(Integer id) {
        try {
            Universidade universidade = this.getUniversidadeRepository().findById(id).get();
            return universidade;
        } catch (Exception ex){
            throw new UniversidadeAPIException("Não foi possivel consultar a universidade! " +id);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void alterar(Universidade universidade) {
        //regra 2 - apenas altera se for altorizada pelo mec
        if (universidade.getAutorizadaMec()){
            for (Curso curso:universidade.getListaCursos()){
                curso.setUniversidade(universidade);
            }
            this.getUniversidadeRepository().save(universidade);
        } else {
            throw new UniversidadeAPIException("Para ser alterada a universidade deve ser autorizado pelo MEC@  ");
        } this.getUniversidadeRepository().save(universidade);

    }

    @Override
    @Transactional (readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void excluir(Integer id) {
        //Regra 3 apenas excluir a universidade se ela não tiver curso  de ADS
        Universidade universidade = this.consultar(id);
        for(Curso curso: universidade.getListaCursos()){
            if("ADS".equals(curso.getCodigoCurso())){
                throw new UniversidadeAPIException("Não é permitido a exclusão de universidade com curso ADS!");
            }
        }
        this.getUniversidadeRepository().deleteById(id);
    }
}
