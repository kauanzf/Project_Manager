package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Projeto;
import gerenciador.projetos.com.br.model.Risco;
import gerenciador.projetos.com.br.model.Situacao;
import gerenciador.projetos.com.br.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    @Autowired
    ProjetoRepository projetoRepository;


    @Override
    public List<Projeto> findAll() {
        return projetoRepository.findAll(Sort.by("nome"));
    }

    @Override
    public Projeto findById(Long id) {
        return projetoRepository.findById(id).get();
    }


    @Override
    public boolean save(Projeto projeto) {
        try{
            projetoRepository.save(projeto);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Projeto> findByRisco(Risco risco) {
        return projetoRepository.findByRisco(risco);
    }

    @Override
    public List<Projeto> findByRiscoIn(List<Risco> riscos){
        return projetoRepository.findByRiscoIn(riscos);
    }

    @Override
    public List<Projeto> findBySituacao(Situacao situacao) {
        return projetoRepository.findBySituacao(situacao);
    }

    @Override
    public List<Projeto> findBySituacaoIn(List<Situacao> situacoes){
        return projetoRepository.findBySituacaoIn(situacoes);
    }



    @Override
    public boolean deleteById(Long id) {
        try{
            projetoRepository.deleteById(id);
            return true;
        } catch (Exception e){
            System.out.println("Erro ao deletar. " + e.getMessage());
            return false;
        }
    }
}
