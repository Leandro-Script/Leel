package com.ifsp.Leel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ifsp.Leel.Model.Cliente;

@Controller
public class HomeController {

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

    @GetMapping("/painel")
    public String painelUsuario() {
        return "painelUsuario.html";
    }
}
