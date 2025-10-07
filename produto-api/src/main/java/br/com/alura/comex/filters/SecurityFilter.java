package br.com.alura.comex.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.comex.dto.ValidacaoTokenResponseDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final RestClient restClient;

  public SecurityFilter() {
    this.restClient = RestClient.builder().build();
  }

  private List<String> endpointsPublicosGet = Arrays.asList("/api/categorias", "/api/produtos");

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if ((HttpMethod.GET.matches(request.getMethod())) && (endpointsPublicosGet.contains(request.getRequestURI()))) {
      filterChain.doFilter(request, response);
    } else {
      var tokenJWT = recuperarToken(request);

      if (isTokenValido(tokenJWT)) {
        filterChain.doFilter(request, response);
      } else {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"mensagem\": \"Token de autenticação inválido.\"}");
        writer.flush();
        return;
      }
    }

  }

  private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }

    return null;
  }

  private boolean isTokenValido(String token) {
    if (token == null || token.isBlank()) {
      return false;
    }
    var retornoValidacao = validarTokenApi(token);
    if (retornoValidacao == null) {
      return false;
    }
    return retornoValidacao.authenticated();
  }

  private ValidacaoTokenResponseDTO validarTokenApi(String token) {
    return restClient.post()
        .uri("http://mpctabl4-161258.ad.mppr.mp.br:8082/usuario-api/api/token/validar")
        .body(token)
        .retrieve()
        .body(ValidacaoTokenResponseDTO.class);
  }

}
