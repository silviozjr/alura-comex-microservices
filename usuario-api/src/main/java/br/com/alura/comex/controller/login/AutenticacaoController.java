package br.com.alura.comex.controller.login;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.DadosTokenJWT;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

  private final AuthenticationManager manager;
  private final TokenService tokenService;
  private final RabbitTemplate rabbitTemplate;

  public AutenticacaoController(AuthenticationManager manager, TokenService tokenService, RabbitTemplate rabbitTemplate,
      MessageConverter jsonMessageConverter) {
    this.manager = manager;
    this.tokenService = tokenService;
    this.rabbitTemplate = rabbitTemplate;
    rabbitTemplate.setMessageConverter(jsonMessageConverter);
  }

  @PostMapping
  public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
    var authentication = manager.authenticate(authenticationToken);

    var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

    var mensagem = new MensagemDadosAutenticacao(
        tokenJWT,
        ((Usuario) authentication.getPrincipal()).getUsername(),
        true,
        "AUTH");

    rabbitTemplate.convertAndSend("usuario.autenticado", mensagem);

    return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
  }
}
