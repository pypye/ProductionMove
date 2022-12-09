package com.example.productmoveapi.controller;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.LoginRequest;
import com.example.productmoveapi.dto.request.ResetPasswordRequest;
import com.example.productmoveapi.dto.request.VerifyAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
  public UserController(UserService userService){
    this.userService = userService;
  }

//  @PostMapping("/sign-up")
//  public ResponseEntity<GeneralResponse<Object>> signUp(@RequestBody CreateAccountRequest createAccountRequest, HttpServletRequest request){
//    return userService.signupAccount(createAccountRequest,request);
//  }
//
//  @PostMapping("/verify")
//  public ResponseEntity<GeneralResponse<Object>> verify(@RequestBody VerifyAccountRequest verifyAccountRequest){
//    return userService.verifySignUp(verifyAccountRequest);
//  }

  @GetMapping("/binh")
  public ResponseEntity<GeneralResponse<Object>> login(){
    return ResponseFactory.success("binh");
  }
  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<Object>> login(@Valid @RequestBody LoginRequest loginRequest){
    return userService.loginAccount(loginRequest);
  }

  @PostMapping("/forgot")
  public ResponseEntity<GeneralResponse<Object>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest){
    return userService.forgotPassword(forgotPasswordRequest,httpServletRequest);
  }

  @PostMapping("/reset")
  public ResponseEntity<GeneralResponse<Object>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
    return userService.resetPassword(resetPasswordRequest);
  }
}