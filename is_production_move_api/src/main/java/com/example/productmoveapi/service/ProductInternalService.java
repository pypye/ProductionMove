package com.example.productmoveapi.service;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 12:06 on 21/12/2022
 */
public interface ProductInternalService {
//  ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest);

  ResponseEntity<GeneralResponse<Object>> getAllCategory();

  ResponseEntity<GeneralResponse<Object>> getAllProduct();
//
//  ResponseEntity<GeneralResponse<Object>> deleteAccount(String id);
//
//  ResponseEntity<GeneralResponse<Object>> updateAccount(String id, UpdateAccountRequest updateAccountRequest);
}
