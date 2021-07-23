package gerenciador.projetos.com.br.service;

import java.util.List;
import gerenciador.projetos.com.br.model.Cargo;

public interface CargoService {
    public Cargo findById(Long id);
    public Cargo findByNome(String nome);
    public List<Cargo> findAll();
    public String validarCargo(Cargo cargo);
    public List<Cargo> findByNomeNot(String nome);

    public boolean save(Cargo cargo);
    public boolean deleteById(Long id);
}