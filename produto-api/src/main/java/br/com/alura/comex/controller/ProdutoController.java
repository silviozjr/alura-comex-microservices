package br.com.alura.comex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.ProdutoRequest;
import br.com.alura.comex.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
  
  private final ProdutoService service;

  public ProdutoController(ProdutoService service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity<Object> salvarProduto(@RequestBody @Valid ProdutoRequest request) {
    service.cadastrar(request.toProduto());
    return null;
  }

  @GetMapping()
  public ResponseEntity<Object> listarCategorias() {
    return ResponseEntity.ok(service.listarTodos());
  }
  

}
