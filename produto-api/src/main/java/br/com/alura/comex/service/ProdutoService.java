package br.com.alura.comex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

  private final ProdutoRepository repositorio;

  public ProdutoService(ProdutoRepository produtoRepository) {
    this.repositorio = produtoRepository;
  }

  @Transactional
  public void cadastrar(Produto produto) {
    repositorio.save(produto);
  }

  public List<Produto> listarTodos() {
    return repositorio.findAll();
  }

}
