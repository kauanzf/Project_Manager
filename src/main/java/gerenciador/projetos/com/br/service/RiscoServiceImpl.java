package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Risco;
import gerenciador.projetos.com.br.repository.RiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiscoServiceImpl implements RiscoService{
    @Autowired
    RiscoRepository riscoRepository;

    @Override
    public Risco findById(Long id){
    return riscoRepository.findById(id).get();
    }

    @Override
    public Risco findByNome(String nome){
        return riscoRepository.findByNome(nome);
    }

    @Override
    public List<Risco> findAll(){
    return riscoRepository.findAll(Sort.by("nome"));
    }

    @Override
    public String validarRisco(Risco risco) {
        String error = null;
        Risco r;
        if (risco.getId() == null){
        r = riscoRepository.findByNome(risco.getNome());
        if (r != null){
            error = "risco ja existe";
        }
        } else {
            r = riscoRepository.findByIdNotAndNome(risco.getId(), risco.getNome());
            if (r != null){
                error = "Ja existe um risco com esse nome";
            }
        }
        return error;
    }
    @Override
    public List<Risco> findByNomeNot(String nome){
        return riscoRepository.findByNomeNot(nome);
    }

    @Override
    public boolean save(Risco risco) {
    try {
        if (risco != null){
            riscoRepository.save(risco);
            return true;
        } else {
            return false;
        }
    } catch (Exception e){
        System.out.println("Erro ao salvar o risco: " + e.getMessage());
        return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            if (id != null || id > 0){
                riscoRepository.deleteById(id);
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            System.out.println("Erro ao deletar o risco" + e.getMessage());
            return false;
        }
    }
}
