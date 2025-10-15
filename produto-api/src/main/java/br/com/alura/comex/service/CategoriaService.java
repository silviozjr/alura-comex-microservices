package br.com.alura.comex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private final CategoriaRepository repositorio;
  private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;
  private static final String TOPIC_NAME = "CATEGORIAS";

  public CategoriaService(CategoriaRepository categoriaRepository,
      KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
    this.repositorio = categoriaRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void cadastrar(Categoria categoria) {
    categoria.setAtivo(true);
    repositorio.save(categoria);
    enviarCategoriaKafka(categoria.getNome());
  }

  public List<Categoria> listarTodos() {
    return repositorio.findAll();
  }

  private void enviarCategoriaKafka(String nomeCategoria) {
    var mensagem = new HashMap<String, String>();
    mensagem.put("Categoria", nomeCategoria);
    kafkaTemplate.send(TOPIC_NAME, mensagem);
    System.out.println("Mensagem enviada para o tópico " + TOPIC_NAME + ": " + nomeCategoria);
  }

}
