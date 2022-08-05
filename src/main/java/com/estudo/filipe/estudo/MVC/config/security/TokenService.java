package com.estudo.filipe.estudo.MVC.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.estudo.filipe.estudo.MVC.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${estudo-MVC.jwt.expiration}")
	private String expiration;

	@Value("${estudo-MVC.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		
		Date now = new Date();
		Date expire = new Date(now.getTime() + Long.parseLong(expiration));
		Usuario logado = (Usuario) authentication.getPrincipal();

		return Jwts.builder().setIssuer("estudo MVC").setSubject(logado.getId().toString()).setIssuedAt(now)
				.setExpiration(expire).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

  public Long getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    
		return Long.parseLong(claims.getSubject());
  }

}
