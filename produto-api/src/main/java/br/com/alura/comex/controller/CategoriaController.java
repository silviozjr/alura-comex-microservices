package br.com.alura.comex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.CategoriaRequest;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final CategoriaService service;

  public CategoriaController(CategoriaService service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity<Object> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {
    service.cadastrar(request.toCategoria());
    return null;
  }

  @GetMapping()
  public ResponseEntity<Object> listarCategorias() {
      return ResponseEntity.ok(service.listarTodos());
  }
  
  

}
