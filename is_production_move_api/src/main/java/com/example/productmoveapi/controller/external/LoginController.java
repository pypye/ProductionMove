package com.example.productmoveapi.controller.external;

import com.example.productmoveapi.dto.request.user_request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.user_request.LoginRequest;
import com.example.productmoveapi.dto.request.user_request.ResetPasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.LoginService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 23:00 on 28/11/2022
 */

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

  private final LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<Object>> login(
      @Valid @RequestBody LoginRequest loginRequest) {
    return loginService.loginAccount(loginRequest);
  }

  @PostMapping("/forgot")
  public ResponseEntity<GeneralResponse<Object>> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    return loginService.forgotPassword(forgotPasswordRequest);
  }

  @PostMapping("/reset")
  public ResponseEntity<GeneralResponse<Object>> resetPassword(
      @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
    return loginService.resetPassword(resetPasswordRequest);
  }
}