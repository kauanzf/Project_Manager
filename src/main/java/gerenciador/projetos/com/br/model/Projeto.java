package gerenciador.projetos.com.br.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=60)
    @NotNull
    private String nome;

    @Size(max=150)
    @NotNull
    private String descricao;

    @OneToOne
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    private Situacao situacao;

    @OneToOne
    @JoinColumn(name = "risco_id", referencedColumnName = "id")
    private Risco risco;


    @NotNull
    private double orcamento;

    @NotNull
    private Date dataInicial;

    @NotNull
    private Date dataFinal;

    @OneToOne
    @JoinColumn(name = "gerente_id", referencedColumnName = "id")
    private Funcionario gerente;

    @ManyToMany
    @JoinTable(name = "projeto_funcionario",
            joinColumns = @JoinColumn(name = "projeto_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="funcionario_id",
                    referencedColumnName = "id")
    )
    private  List<Funcionario> funcionarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Risco getRisco() {
        return risco;
    }
    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public Situacao  getSituacao() {
        return situacao;
    }
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Funcionario getGerente() {
        return gerente;
    }

    public void setGerente(Funcionario gerente) {
        this.gerente = gerente;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", situacao ='" + situacao.getNome() + '\'' +
                ", risco='" + risco.getNome() + '\'' +
                ", orcamento=" + orcamento +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                '}';
    }

}
