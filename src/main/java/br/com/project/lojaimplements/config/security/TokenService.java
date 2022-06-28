package br.com.project.lojaimplements.config.security;

import java.util.Date;

import br.com.project.lojaimplements.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${project.jwt.expiration}")
	private String experation;
	
	@Value("${project.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		
		Usuario login = (Usuario) authentication.getPrincipal();
		Date now = new Date();
		Date dateEx = new Date(now.getTime() + Long.parseLong(experation));
		
		
		return Jwts.builder()
				.setIssuer("API Project Loja")
				.setSubject(login.getId().toString())
				.setIssuedAt(now)
				.setExpiration(dateEx)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
