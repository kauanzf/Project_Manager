package gerenciador.projetos.com.br.controller;

import gerenciador.projetos.com.br.model.Risco;
import gerenciador.projetos.com.br.service.RiscoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RiscoController {

    @Autowired
    RiscoServiceImpl riscoService;

    @GetMapping("/risco/list")
    public String list(Model model){
    model.addAttribute("riscos", riscoService.findAll());
        return "risco/list";
    }

    @GetMapping("/risco/add")
    public String add(Model model){
        model.addAttribute("risco", new Risco());
        return "risco/add";
    }

    @PostMapping("/risco/save")
    public String save(Risco risco, Model model){
        String msgErro = riscoService.validarRisco(risco);
        if (msgErro != null) {
            model.addAttribute("risco", risco);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", msgErro);
            if(risco.getId() == null) return "risco/add";
            else return "risco/edit";
        }

        if (riscoService.save(risco)){
            return "redirect:/risco/list";
        } else {
            model.addAttribute("risco", risco);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", "Erro ao salvar");
            return "risco/add";
        }
    }

    @GetMapping("/risco/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("risco", riscoService.findById(id));
        return "risco/edit";
    }

    @GetMapping("/risco/delete/{id}")
    public String delete(@PathVariable Long id){
        if (riscoService.deleteById(id)){
            return "redirect:/risco/list";
        } else {
            return "redirect:/risco/list";
        }
    }
}
