package br.com.rodrigo.projetoExemplo.web.controller;
import br.com.rodrigo.projetoExemplo.domain.Cargo;
import br.com.rodrigo.projetoExemplo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService service;
    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Cargo cargo, RedirectAttributes attr) {
        service.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargo/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", service.buscarPorId(id));
        return "/cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Cargo cargo, RedirectAttributes attr) {
        service.editar(cargo);
        attr.addFlashAttribute("success", "Cargo editado com sucesso.");
        return "redirect:/cargo/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {

//        if (service.depertamentoTemCargos(id)) {
//            model.addAttribute("fail",
//                    "Departamento não removido. Possui cargo(s) vinculado(s).");
//        } else {
            service.excluir(id);
            model.addAttribute("success",
                    "Departamento excluído com sucesso.");
//        }

        return listar(model);
    }
}
