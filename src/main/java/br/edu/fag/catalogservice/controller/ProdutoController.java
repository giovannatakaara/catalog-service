package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapperDto.ProdutoDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final IProdutoService iProdutoService;

    public ProdutoController(IProdutoService iProdutoService) {
        this.iProdutoService = iProdutoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO) {

        if (produtoDTO.getNome() == null || produtoDTO.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (produtoDTO.getPreco() == null || produtoDTO.getPreco() <= 0) {
            throw new RuntimeException("O preço do produto deve ser um número válido.");
        }

        if (produtoDTO.getDescricao() == null) {
            throw new RuntimeException("Adicione uma descrição.");
        }

        ProdutoDomain produtoDomain = ProdutoDtoMapper.toDomain(produtoDTO);
        ProdutoDomain produtoCriado = iProdutoService.criar(produtoDomain);

        ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

    }

}


