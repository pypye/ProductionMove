package com.example.productmoveapi.service.general;

import com.example.productmoveapi.dto.request.user_request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.user_request.LoginRequest;
import com.example.productmoveapi.dto.request.user_request.ResetPasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 22:55 on 28/11/2022
 */
public interface LoginService {

  ResponseEntity<GeneralResponse<Object>> loginAccount(LoginRequest loginRequest);

  ResponseEntity<GeneralResponse<Object>> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

  ResponseEntity<GeneralResponse<Object>> resetPassword(ResetPasswordRequest resetPasswordRequest);
}
