package com.example.productmoveapi.service;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.UpdateAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 17:05 on 10/12/2022
 */
public interface AdminManageUserService {

  ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest);

  ResponseEntity<GeneralResponse<Object>> getAllAccount();

  ResponseEntity<GeneralResponse<Object>> deleteAccount(String id);

  ResponseEntity<GeneralResponse<Object>> updateAccount(String id, UpdateAccountRequest updateAccountRequest);
}
