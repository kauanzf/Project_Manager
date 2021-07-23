package gerenciador.projetos.com.br.controller;

import gerenciador.projetos.com.br.model.Cargo;
import gerenciador.projetos.com.br.model.Projeto;
import gerenciador.projetos.com.br.model.Risco;
import gerenciador.projetos.com.br.model.Situacao;
import gerenciador.projetos.com.br.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjetoController {
@Autowired
    ProjetoServiceImpl projetoService;

@Autowired
FuncionarioServiceImpl funcionarioService;

@Autowired
CargoServiceImpl cargoService;

@Autowired
RiscoServiceImpl riscoService;

@Autowired
SituacaoServiceImpl situacaoService;



    @GetMapping("/projeto/list")
    public String list(Model model){
        model.addAttribute("projetos", projetoService.findAll());
        return "projeto/list";
    }

    @GetMapping("/projeto/add")
    public String add(Model model){
        model.addAttribute("projeto", new Projeto());
        Risco risco = riscoService.findByNome("Risco");
        Situacao situacao = situacaoService.findByNome("situacao");

        model.addAttribute("riscos", riscoService.findAll());

        model.addAttribute("situacoes", situacaoService.findAll());



        Cargo cargo = cargoService.findByNome("Gerente");


        List<Cargo> cargos = cargoService.findByNomeNot("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        model.addAttribute("funcionarios",funcionarioService.findByCargoIn(cargos));

        return "projeto/add";
    }

    @GetMapping("/projeto/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("projeto", projetoService.findById(id));
        Cargo cargo = cargoService.findByNome("Gerente");
        List<Cargo> cargos = cargoService.findByNomeNot("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        model.addAttribute("funcionarios",funcionarioService.findByCargoIn(cargos));


        return "projeto/edit";
    }


    @PostMapping("/projeto/save")
    public String save(Projeto projeto, Model model){


        if (projetoService.save(projeto)){
            return "redirect:/projeto/list";
        } else {

            return "redirect:/projeto/list";
        }
    }

    @GetMapping("/projeto/delete/{id}")
    public String delete(@PathVariable Long id){
        projetoService.deleteById(id);
        return "redirect:/projeto/list";
    }
}
