package br.edu.pucgoias.universidade.api.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TB_UNIVERSIDADE")
public class Universidade {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversidade;

    @Column
    private String nomeUniversidade;

    @Column
    private Boolean autorizadaMec;

    @JsonIgnoreProperties("universidade") // para não gerar referencias infinitas
    @OneToMany(mappedBy = "universidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> listaCursos;

    public Integer getIdUniversidade() {
        return idUniversidade;
    }

    public void setIdUniversidade(Integer idUniversidade) {
        this.idUniversidade = idUniversidade;
    }

    public String getNomeUniversidade() {
        return nomeUniversidade;
    }

    public void setNomeUniversidade(String nomeUniversidade) {
        this.nomeUniversidade = nomeUniversidade;
    }

    public Boolean getAutorizadaMec() {
        return autorizadaMec;
    }

    public void setAutorizadaMec(Boolean autorizadaMec) {
        this.autorizadaMec = autorizadaMec;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
