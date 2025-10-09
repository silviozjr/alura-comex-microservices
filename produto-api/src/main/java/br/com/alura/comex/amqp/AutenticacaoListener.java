package br.com.alura.comex.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.alura.comex.model.Autenticacao;
import br.com.alura.comex.repository.AutenticacaoRepository;

@Component
public class AutenticacaoListener {

  @Autowired
  private AutenticacaoRepository autenticacaoRepository;

  @RabbitListener(queues = "usuario.autenticado")
  public void recebeMensagem(MensagemDadosAutenticacao mensagem) {

    Autenticacao aut = new Autenticacao();
    aut.setNome(mensagem.nome());
    aut.setToken(mensagem.token());
    aut.setAtivo(mensagem.ativo());
    aut.setTipo(mensagem.tipo());

    autenticacaoRepository.save(aut);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
