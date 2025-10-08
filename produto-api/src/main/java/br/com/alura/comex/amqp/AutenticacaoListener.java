package br.com.alura.comex.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoListener {
  @RabbitListener(queues = "usuario.autenticado")
  public void recebeMensagem(MensagemDadosAutenticacao mensagem) {
    System.out.println("Recebi a mensagem " + mensagem.toString());
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
