package com.example.productmoveapi.service;

import com.example.productmoveapi.dto.request.ChangePasswordRequest;
import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.LoginRequest;
import com.example.productmoveapi.dto.request.ResetPasswordRequest;
import com.example.productmoveapi.dto.request.VerifyAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 22:55 on 28/11/2022
 */
public interface UserService {
  ResponseEntity<GeneralResponse<Object>> loginAccount(LoginRequest loginRequest);
  ResponseEntity<GeneralResponse<Object>> logoutAccount();
  ResponseEntity<GeneralResponse<Object>> verifySignUp(VerifyAccountRequest verifyAccountRequest);
  ResponseEntity<GeneralResponse<Object>> changePassword(ChangePasswordRequest changePasswordRequest);
  ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest, HttpServletRequest request);
  ResponseEntity<GeneralResponse<Object>> forgotPassword(ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest);
  ResponseEntity<GeneralResponse<Object>> resetPassword(ResetPasswordRequest resetPasswordRequest);
}