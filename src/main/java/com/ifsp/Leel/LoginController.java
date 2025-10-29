package com.ifsp.Leel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifsp.Leel.Model.Cliente;
import com.ifsp.Leel.Repository.ClienteRepository;

@Controller
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/loja")
    public String loja() {
        return "page.html";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "cadastroLogin.html";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String nome, @RequestParam String senha,
            Model model) {

        Cliente cliente = clienteRepository.findByNomeAndSenha(nome, senha);

        if (cliente != null) {
            model.addAttribute("usuario", cliente.getNome());
            return "redirect:/loja";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "cadastroLogin";
        }
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
            @RequestParam String cpf, Model model) {

        Cliente cliente = new Cliente(nome, email, senha, cpf);

        clienteRepository.save(cliente);
        model.addAttribute("cliente", cliente);

        return "redirect:/login";
    }

}
