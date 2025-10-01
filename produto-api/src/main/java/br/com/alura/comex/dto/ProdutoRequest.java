package br.com.alura.comex.dto;

import java.util.List;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(
  @NotBlank  
  String nome,

  @NotNull
  @Min(value = 0)
  double preco,

  String descricao,

  @NotNull
  Long qtdEstoque,

  @NotNull
  @Size(min = 1)
  List<Long> categorias
) {

  public Produto toProduto() {
    Produto p = new Produto();
    p.setNome(this.nome);
    p.setDescricao(this.descricao);
    p.setPreco(this.preco);
    p.setQtdEstoque(this.qtdEstoque);
    this.categorias.forEach(c -> p.adicionaCategoria(new Categoria(c)));
    return p;
  }

}
