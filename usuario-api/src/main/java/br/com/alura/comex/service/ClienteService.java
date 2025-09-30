package br.com.alura.comex.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final PasswordEncoder passwordEncoder;

  public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
    this.clienteRepository = clienteRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void cadastrar(Cliente cliente) {
    cliente.getUsuario().setSenha(passwordEncoder.encode(cliente.getUsuario().getSenha()));
    clienteRepository.save(cliente);
  }

  public List<Cliente> listarTodos() {
    return clienteRepository.findAll();
  }

}
