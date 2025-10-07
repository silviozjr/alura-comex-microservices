package br.com.alura.comex.dto;

public record ValidacaoTokenResponseDTO(
  String name,
  boolean authenticated
) {

}