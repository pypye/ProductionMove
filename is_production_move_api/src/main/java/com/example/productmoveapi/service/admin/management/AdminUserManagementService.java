package com.example.productmoveapi.service.admin.management;

import com.example.productmoveapi.dto.request.user_request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.user_request.UpdateAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 17:05 on 10/12/2022
 */
public interface AdminUserManagementService {

  ResponseEntity<GeneralResponse<Object>> signupAccount(CreateAccountRequest createAccountRequest);

  ResponseEntity<GeneralResponse<Object>> getAllAccount();

  ResponseEntity<GeneralResponse<Object>> deleteAccount(String id);

  ResponseEntity<GeneralResponse<Object>> updateAccount(String id, UpdateAccountRequest updateAccountRequest);
}
