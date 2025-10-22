package com.ifsp.Leel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "index.html";
    }

    @GetMapping("/loja")
    public String loja() {
        return "page.html";
    }

    @GetMapping("/produtos")
    public String produtos() {
        return "listaProdutos.html";
    }

    @GetMapping("/detalhes")
    public String detalhes() {
        return "detalhesProduto.html";
    }

    @GetMapping("/cadastrarProduto")
    public String cadastrarProduto() {
        return "cadastroProduto.html";
    }

    @GetMapping("/cadastrarVendedor")
    public String cadastrarVendedor() {
        return "cadastroVendedor.html";
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastrar.html";
    }
}
