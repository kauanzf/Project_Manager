package gerenciador.projetos.com.br.controller;



import gerenciador.projetos.com.br.model.Situacao;
import gerenciador.projetos.com.br.service.SituacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SituacaoController {
    @Autowired
    SituacaoServiceImpl situacaoService;

    @GetMapping("/situacao/list")
    public String list(Model model){
        model.addAttribute("situacoes", situacaoService.findAll());
        return "situacao/list";
    }

    @GetMapping("/situacao/add")
    public String add(Model model){
        model.addAttribute("situacao", new Situacao());
        return "situacao/add";
    }

    @PostMapping("/situacao/save")
    public String save(Situacao situacao, Model model){
        String msgErro = situacaoService.validarSituacao(situacao);
        if (msgErro != null) {
            model.addAttribute("situacao", situacao);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", msgErro);
            if(situacao.getId() == null) return "situacao/add";
            else return "situacao/edit";
        }

        if (situacaoService.save(situacao)){
            return "redirect:/situacao/list";
        } else {
            model.addAttribute("situacao", situacao);
            model.addAttribute("erro", true);
            model.addAttribute("erroMsg", "Erro ao salvar");
            return "situacao/add";
        }
    }

    @GetMapping("/situacao/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("situacao", situacaoService.findById(id));
        return "situacao/edit";
    }

    @GetMapping("/situacao/delete/{id}")
    public String delete(@PathVariable Long id){
        if (situacaoService.deleteById(id)){
            return "redirect:/situacao/list";
        } else {
            return "redirect:/situacao/list";
        }
    }
}
