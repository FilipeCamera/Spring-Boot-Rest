package com.estudo.filipe.estudo.MVC.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.estudo.filipe.estudo.MVC.model.Usuario;
import com.estudo.filipe.estudo.MVC.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

  final TokenService tokenService;
  final UsuarioRepository usuarioRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    String token = recuperarToken(request);
    
    boolean valid = tokenService.isValidToken(token);

    if(valid) {
      autenticarUsuario(token);
    }
    filterChain.doFilter(request, response); // como se fosse o next do express
  }

  private void autenticarUsuario(String token) {
    Long usuarioId = tokenService.getUsuarioId(token);
    Usuario usuario = usuarioRepository.findById(usuarioId).get();
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");

    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.substring(7, token.length());
  }
  
}
