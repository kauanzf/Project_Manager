package gerenciador.projetos.com.br.service;


import gerenciador.projetos.com.br.model.Situacao;
import gerenciador.projetos.com.br.repository.SituacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituacaoServiceImpl implements SituacaoService {
    @Autowired
    SituacaoRepository situacaoRepository;


    @Override
    public Situacao findById(Long id) {
        return situacaoRepository.findById(id).get();
    }

    @Override
    public Situacao findByNome(String nome) {
        return situacaoRepository.findByNome(nome);
    }

    @Override
    public List<Situacao> findAll() {
        return situacaoRepository.findAll(Sort.by("nome"));
    }

    @Override
    public String validarSituacao(Situacao situacao) {
        String error = null;
        Situacao s;
        if (situacao.getId() == null) {
            s = situacaoRepository.findByNome(situacao.getNome());
            if (s != null) {
                error = "o nome informado: " + situacao.getNome() + ", já está cadastrado";
            }
        } else {
            s = situacaoRepository.findByIdNotAndNome(situacao.getId(), situacao.getNome());
            if (s != null) {
                error = "já existe uma situação com esse nome";
            }
        }
        if (situacao.getNome().length() == 0) {
            error += "O nome da situação é obrgatória";
        }

        if (situacao.getNome().length() > 100) {
            error += " O nome do risco pode de ter no máximo 100 caracteres.";
        }
        return error;
    }

    @Override
    public List<Situacao> findByNomeNot(String nome) {
        return situacaoRepository.findByNomeNot(nome);
    }

    @Override
    public boolean save(Situacao situacao) {
        try {
            var error = this.validarSituacao(situacao);
            if (error == null) {
                if (situacao != null) {
                    situacaoRepository.save(situacao);
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new Exception(error);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar a situação" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            if (id != null || id > 0){
                situacaoRepository.deleteById(id);
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            System.out.println("Erro ao deletar a situação" + e.getMessage());
            return false;
        }
    }

}
