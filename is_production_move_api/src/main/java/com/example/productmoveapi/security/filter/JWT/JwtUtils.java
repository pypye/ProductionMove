package com.example.productmoveapi.security.filter.JWT;

import static com.example.productmoveapi.security.SecurityConstants.SECRET;

import com.example.productmoveapi.security.filter.service.UserDetailsImplement;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Binh Nguyen Thai at 10:19 on 05/12/2022
 */
@Component
public class JwtUtils {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  public String generateJWTByUsername(String username){
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject((username))
        .setIssuedAt(new Date())
        //.setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME * 500))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public String generateJWT(Authentication authentication){

    UserDetailsImplement userPrincipal = (UserDetailsImplement) authentication.getPrincipal();
    return generateJWTByUsername(userPrincipal.getUsername());
  }


  public String getUserNameFromJwtToken(String token, String secret) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
      throw e;
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
}