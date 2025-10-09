package br.com.alura.comex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autenticacao")
public class Autenticacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;
  private String nome;
  private boolean ativo;
  private String tipo;

  public Autenticacao() {
  }

  public Autenticacao(Long id, String token, String nome, boolean ativo, String tipo) {
    this.id = id;
    this.token = token;
    this.nome = nome;
    this.ativo = ativo;
    this.tipo = tipo;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public boolean isAtivo() {
    return ativo;
  }
  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  
}
