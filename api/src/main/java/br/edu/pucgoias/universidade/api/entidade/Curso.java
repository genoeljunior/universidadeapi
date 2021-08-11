package br.edu.pucgoias.universidade.api.entidade;

import javax.persistence.*;

@Entity
@Table(name="TB_CURSOS")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idCurso;

    @Column
    private String codigoCurso;

    @Column
    private String nomeCurso;

    @ManyToOne
    @JoinColumn(name = "idUniversidade", nullable = false)
    private Universidade universidade;

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }
}
