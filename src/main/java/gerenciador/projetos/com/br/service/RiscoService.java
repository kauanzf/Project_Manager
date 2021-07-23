package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Risco;

import java.util.List;

public interface RiscoService {
    public Risco findById(Long id);
    public Risco findByNome(String nome);
    public List<Risco> findAll();
    public String validarRisco(Risco risco);
    public List<Risco> findByNomeNot(String nome);

    public boolean save(Risco risco);
    public boolean deleteById(Long id);
}
