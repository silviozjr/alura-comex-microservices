package br.com.alura.comex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private final CategoriaRepository repositorio;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.repositorio = categoriaRepository;
  }

  public void cadastrar(Categoria categoria) {    
    categoria.setAtivo(true);
    repositorio.save(categoria);
  }

  public List<Categoria> listarTodos() {
    return repositorio.findAll();
  }

}
