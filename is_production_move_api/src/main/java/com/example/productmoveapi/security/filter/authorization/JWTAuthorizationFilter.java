package com.example.productmoveapi.security.filter.authorization;

import static com.example.productmoveapi.security.SecurityConstants.HEADER_STRING;
import static com.example.productmoveapi.security.SecurityConstants.SECRET;
import static com.example.productmoveapi.security.SecurityConstants.TOKEN_PREFIX;

import com.example.productmoveapi.security.filter.JWT.JwtUtils;
import com.example.productmoveapi.security.filter.service.UserDetailsServiceImplement;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Binh Nguyen Thai at 10:19 on 05/12/2022
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsServiceImplement userDetailsServiceImplement;

  @Autowired
  private JwtUtils jwtUtils;

  private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    try {
      String jwt = parseJwt(request, HEADER_STRING);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt, SECRET);

        UserDetails userDetails = userDetailsServiceImplement.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
//      logger.error("Cannot set user authentication: {}", e.getMessage());
    }
    response.setContentType("application/xml; charset=utf-8");
    chain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request, String token) {
    String headerAuth = request.getHeader(token);

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_PREFIX)) {
      return headerAuth.substring(7);
    }

    return null;
  }
}
