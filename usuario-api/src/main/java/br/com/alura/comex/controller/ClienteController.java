package br.com.alura.comex.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.ClienteRequest;
import br.com.alura.comex.service.ClienteService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("api/clientes")
public class ClienteController {

  private final ClienteService service;

  public ClienteController(ClienteService service) {
    this.service = service;
  }

  @PostMapping
  public void cadastrarCliente(@RequestBody @Valid ClienteRequest request) {
    service.cadastrar(request.toCliente());
  }

  
}
