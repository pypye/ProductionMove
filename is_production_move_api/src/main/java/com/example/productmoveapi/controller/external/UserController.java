package com.example.productmoveapi.controller.external;

import com.example.productmoveapi.dto.request.user_request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.user_request.LoginRequest;
import com.example.productmoveapi.dto.request.user_request.ResetPasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.UserService;
import javax.servlet.http.HttpServletRequest;
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
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<Object>> login(
      @Valid @RequestBody LoginRequest loginRequest) {
    return userService.loginAccount(loginRequest);
  }

  @PostMapping("/forgot")
  public ResponseEntity<GeneralResponse<Object>> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest,
      HttpServletRequest httpServletRequest) {
    return userService.forgotPassword(forgotPasswordRequest, httpServletRequest);
  }

  @PostMapping("/reset")
  public ResponseEntity<GeneralResponse<Object>> resetPassword(
      @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
    return userService.resetPassword(resetPasswordRequest);
  }
}