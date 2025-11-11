package com.ifsp.Leel.Controller;

import com.ifsp.Leel.Model.Produto;
import com.ifsp.Leel.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/img/uploads/";

    @GetMapping("/cadastrarProduto")
    public String showCadastroProdutoForm() {
        return "cadastroProduto.html";
    }

    @PostMapping("/cadastrarProduto")
    public String cadastrarProduto(
            @RequestParam("nome") String nome,
            @RequestParam("valor") double valor,
            @RequestParam("descricao") String descricao,
            @RequestParam("quantidade") int quantidade,
            @RequestParam("categoria") String categoria,
            @RequestParam("imagemFile") MultipartFile imagemFile,
            Model model) {

        String nomeArquivoImagem = null;

        if (imagemFile != null && !imagemFile.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String extensao = "";
                String nomeOriginal = imagemFile.getOriginalFilename();
                if (nomeOriginal != null && nomeOriginal.contains(".")) {
                    extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
                }
                nomeArquivoImagem = UUID.randomUUID().toString() + extensao;

                Path caminhoArquivo = uploadPath.resolve(nomeArquivoImagem);
                Files.copy(imagemFile.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

                nomeArquivoImagem = "/img/uploads/" + nomeArquivoImagem;

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("erro", "Falha ao salvar a imagem.");
                return "cadastroProduto";
            }
        } else {
            nomeArquivoImagem = "/img/default.png";
        }

        Produto produto = new Produto(nome, descricao, nomeArquivoImagem, categoria, valor, quantidade);

        try {
            produtoRepository.salvar(produto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Falha ao salvar o produto no banco de dados.");
            return "cadastroProduto";
        }

        return "redirect:/produtos";
    }
}