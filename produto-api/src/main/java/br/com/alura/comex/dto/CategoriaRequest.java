package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
  @NotBlank
  @Size(min = 2)
  String nome
) {
  public Categoria toCategoria() {
    Categoria c = new Categoria();
    c.setNome(this.nome);
    return c;
  }
}
