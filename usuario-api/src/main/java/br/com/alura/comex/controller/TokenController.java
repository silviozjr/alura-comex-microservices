package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.service.TokenService;

@RestController
@RequestMapping("/api/token")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository repository;

  @PostMapping("validar")
  public ResponseEntity<Object> validarToken(@RequestBody String tokenJWT) {

    try {
      if (tokenJWT != null) {
        var subject = tokenService.getSubject(tokenJWT);
        var usuario = repository.findByLogin(subject);

        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        var usuarioToken = new ValidacaoTokenResponseDTO(authentication.getName(), authentication.isAuthenticated(), authentication.getAuthorities());
        
        return ResponseEntity.ok(usuarioToken);
      }

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

  }

}
