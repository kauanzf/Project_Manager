package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Projeto;
import gerenciador.projetos.com.br.model.Risco;
import gerenciador.projetos.com.br.model.Situacao;

import java.util.List;

public interface ProjetoService {

    public List<Projeto> findAll();
    public Projeto findById(Long id);

    public List<Projeto> findByRisco(Risco risco);
    public List<Projeto> findByRiscoIn(List<Risco> riscos);

    public List<Projeto> findBySituacao(Situacao situacao);
    public List<Projeto> findBySituacaoIn(List<Situacao> situacoes);


    public boolean save(Projeto projeto);
    public boolean deleteById(Long id);

}
