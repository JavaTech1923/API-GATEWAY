package com.gof.gateway.util;

import java.security.Key;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Configuration
public class JwtUtil {
	
	  private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	  public void validateToken(final String token) {
			 Jws <Claims> claimsJws = Jwts
		      .parserBuilder()
		      .setSigningKey(getSignInKey())
		      .build()
		      .parseClaimsJws(token);
		      
		  }
	  

	  private Key getSignInKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
	    return Keys.hmacShaKeyFor(keyBytes);
	  }

}
