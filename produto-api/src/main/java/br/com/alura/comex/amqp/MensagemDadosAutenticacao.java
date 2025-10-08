package br.com.alura.comex.amqp;

public record MensagemDadosAutenticacao(
  String token,
  String nome,
  boolean ativo,
  String tipo
) {

}
