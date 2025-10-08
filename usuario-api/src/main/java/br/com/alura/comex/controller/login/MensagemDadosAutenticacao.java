package br.com.alura.comex.controller.login;

public record MensagemDadosAutenticacao(
  String token,
  String nome,
  boolean ativo,
  String tipo
) {

}
