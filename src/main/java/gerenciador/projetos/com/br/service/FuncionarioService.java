package gerenciador.projetos.com.br.service;

import gerenciador.projetos.com.br.model.Cargo;
import gerenciador.projetos.com.br.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    public List<Funcionario> findAll();
    public Funcionario findById(Long id);
    public Funcionario findByEmail(String email);
    public String validarFuncionario(Funcionario funcionario);

    public List<Funcionario> findByCargo(Cargo cargo);
    public List<Funcionario> findByCargoIn(List<Cargo> cargos);


    public boolean save(Funcionario funcionario);
    public boolean deleteById(Long id);
}
