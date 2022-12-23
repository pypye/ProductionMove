package com.example.productmoveapi.dto.response;

import static com.example.productmoveapi.security.SecurityConstants.TOKEN_PREFIX;

import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:25 on 05/12/2022
 */
@Data
public class LoginResponse {

  private String token;
  private String type = TOKEN_PREFIX;
  private String id;
  private String username;
  private String email;
  private String role;

  public LoginResponse(String token, String id, String username, String email, String role) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
  }
}
