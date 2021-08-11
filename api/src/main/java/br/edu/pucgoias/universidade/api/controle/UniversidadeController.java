package br.edu.pucgoias.universidade.api.controle;


import br.edu.pucgoias.universidade.api.entidade.Universidade;
import br.edu.pucgoias.universidade.api.negocio.UniversidadeService;
import br.edu.pucgoias.universidade.api.util.UniversidadeAPIException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
@EnableSwagger2
@RestController
public class UniversidadeController {

    @Autowired
    UniversidadeService universidadeService;

    /**
     * Metodo que lista as universidades
     * @return lista de universidades
     */
    @GetMapping(value = "/listar")
    @ApiOperation("Metodo que Lista as Universidades")
    public List<Universidade> listar(){
        List<Universidade> lista = universidadeService.listar();
        return lista;
    }

    /**
     * Metodo que consulta uma universidade por id
     * @param id id do usuário
     * @return objeto universidade
     */
    @GetMapping(value = "/consultar/{id}")
    @ApiOperation("Metodo que consulta uma universidade por id")
    public Universidade consultar(@PathVariable Integer id){
        return universidadeService.consultar(id);
    }

    /**
     * Metodo que salva (inclui ou altera) uma universidade
     * @param universidade
     * @return universidade alterada ou incluida
     */
    @PostMapping(value = "/salvar")
    @ApiOperation("Metodo que salva(inlui ou altera) uma universidade")
    public Universidade salvar(@RequestBody Universidade universidade){
        try{
            if (universidade.getIdUniversidade()==null){
                universidade = universidadeService.incluir(universidade);
            } else {
                universidadeService.alterar(universidade);
            }
            return universidade;
        } catch (UniversidadeAPIException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getReason());
        }
    }

    /**
     * Metodo que exclui uma universidade pelo id
     * @param id id da universidade a ser excluida
     */
    @DeleteMapping(value = "/excluir-universidade/{id}")
    @ApiOperation("Metodo que exclui uma universidade pelo id")
    public void excluir(@PathVariable Integer id){
        universidadeService.excluir(id);
    }

}
