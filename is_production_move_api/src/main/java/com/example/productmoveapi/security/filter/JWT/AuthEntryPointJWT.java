package com.example.productmoveapi.security.filter.JWT;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author Binh Nguyen Thai at 10:19 on 05/12/2022
 */
@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJWT.class);

  @Override
  public void commence(
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      org.springframework.security.core.AuthenticationException e)
      throws IOException {
//    logger.error("Unauthorized error: {}", e.getMessage());
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
  }
}