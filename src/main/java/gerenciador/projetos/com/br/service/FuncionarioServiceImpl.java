package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Cargo;
import gerenciador.projetos.com.br.model.Funcionario;
import gerenciador.projetos.com.br.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario>  findAll(){
    return funcionarioRepository.findAll(Sort.by("nome"));
    }

    @Override
    public Funcionario findById(Long id){
    return funcionarioRepository.findById(id).get();
    }

    @Override
    public Funcionario findByEmail(String email){
    return funcionarioRepository.findByEmail(email);
    }

    @Override
    public boolean save (Funcionario funcionario) {
        try{
            if (funcionario != null){
            funcionarioRepository.save(funcionario);
            return true;
            } else {
            return false;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public String validarFuncionario(Funcionario funcionario){
        String error = null;
        Funcionario f;

        if (funcionario.getId() == null){
        f = funcionarioRepository.findByNome(funcionario.getNome());
        if (f != null){
            error = "Nome já existe.";
            }
        f =funcionarioRepository.findByEmail(funcionario.getEmail());
            if ( f != null){
                if (error != null) error += " ";
                error = "Já existe um funcionário com esse e-mail.";
            }
        } else {
            f = funcionarioRepository.findByIdNotAndNome(funcionario.getId(), funcionario.getNome());
            if ( f != null){
                error = " Já existe um funcionário com esse nome.";
            }

            f = funcionarioRepository.findByIdNotAndEmail(funcionario.getId(), funcionario.getEmail());
            if (f != null){
                if (error != null) error += " ";
                error = " Já existe um funcionário com esse e-mail.";
            }

        }
        return error;
    }

    @Override
    public List<Funcionario> findByCargo(Cargo cargo) {
        return funcionarioRepository.findByCargo(cargo);
    }

    @Override
    public List<Funcionario> findByCargoIn(List<Cargo> cargos) {

        return funcionarioRepository.findByCargoIn(cargos);
    }

    public boolean deleteById(Long id){
        try{
            funcionarioRepository.deleteById(id);
            return  true;
        }catch(Exception e){
            return false;
        }
    }
}


