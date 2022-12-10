package com.example.productmoveapi.service;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 17:05 on 10/12/2022
 */
public interface AdminManageUserService {

  //  ResponseEntity<GeneralResponse<Object>> logoutAccount();
//  ResponseEntity<GeneralResponse<Object>> verifySignUp(VerifyAccountRequest verifyAccountRequest);
//  ResponseEntity<GeneralResponse<Object>> changePassword(ChangePasswordRequest changePasswordRequest);
  ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest);
}
