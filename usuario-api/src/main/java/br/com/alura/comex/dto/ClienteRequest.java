package br.com.alura.comex.dto;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
    @Length(min = 11, max = 11)
    String cpf,

    @Length(max = 256)
    @NotBlank
    String nome,

    @Email
    @Length(max = 256)
    String email,

    @Length(max = 30)
    String telefone,

    @Length(max = 256)
    String logradouro,

    @Length(max = 256)
    String bairro,

    @Length(max = 256)
    String cidade,

    @Length(max = 2)
    String estado,

    @Length(max = 8)
    String cep,

    @Length(min = 6)
    String senha
) {

  public Cliente toCliente() {
    var c = new Cliente();
    c.setNome(this.nome);
    c.setCpf(this.cpf);
    c.setEmail(this.email);
    c.setTelefone(this.telefone);
    c.setLogradouro(this.logradouro);
    c.setBairro(this.bairro);
    c.setCidade(this.cidade);
    c.setEstado(this.estado);
    c.setCep(this.cep);
    
    var u = new Usuario();
    u.setLogin(this.email);
    u.setSenha(this.senha);

    c.setUsuario(u);
    
    return c;
  }

}
