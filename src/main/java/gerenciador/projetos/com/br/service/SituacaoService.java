package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Situacao;

import java.util.List;

public interface SituacaoService {

    public Situacao findById(Long id);

    public Situacao findByNome(String nome);

    public List<Situacao> findAll();

    public String validarSituacao(Situacao situacao);

    public List<Situacao> findByNomeNot(String nome);

    public boolean save(Situacao situacao);

    public boolean deleteById(Long id);
}
