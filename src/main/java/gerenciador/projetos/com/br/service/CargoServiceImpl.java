package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Cargo;
import gerenciador.projetos.com.br.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements  CargoService {
    @Autowired
    CargoRepository cargoRepository;


    @Override
    public Cargo findById(Long id) {
        return cargoRepository.findById(id).get();
    }

    @Override
    public Cargo findByNome(String nome) {
        return cargoRepository.findByNome(nome);
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public String validarCargo(Cargo cargo) {
        String error = null;
        Cargo c;
        if (cargo.getId() == null) {//Quando for um novo registro.
            c = cargoRepository.findByNome(cargo.getNome());
            if (c != null){
                error = "O nome do cargo informado:  " + cargo.getNome() + ", já está cadastro!";
            }
        } else {//Quando for Alteração
            c = cargoRepository.findByIdNotAndNome(cargo.getId(), cargo.getNome());
            if ( c != null){
                error = "Já existe um cargo com esse nome.";
            }
        }

        if(cargo.getNome().length() == 0){
            error += " O nome do cargo é obrigatório.";
        }

        if(cargo.getNome().length() > 100){
            error += " O nome do cargo de ter no máximo 100 caracteres.";
        }

        return error;
    }

    @Override
    public List<Cargo> findByNomeNot(String nome) {
        return cargoRepository.findByNomeNot(nome);
    }

    @Override
    public boolean save(Cargo cargo) {
        try{
            var error = this.validarCargo(cargo);
            if (error==null)//Se estiver tudo ok.
            {
                if (cargo != null){
                    cargoRepository.save(cargo);
                    return true;
                } else {
                    return false;
                }
            }else{
                throw new Exception(error);
            }
        } catch (Exception e){
            System.out.println("Erro ao salvar o cargo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        cargoRepository.deleteById(id);
        return (id > 0);
    }
}
