package br.com.alura.comex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Autenticacao;

public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long> {
  Optional<Autenticacao> findByToken(String token);
}
