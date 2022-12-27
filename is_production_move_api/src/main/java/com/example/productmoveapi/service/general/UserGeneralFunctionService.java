package com.example.productmoveapi.service.general;

import com.example.productmoveapi.dto.request.user_request.ChangePasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 13:21 on 14/12/2022
 */
public interface UserGeneralFunctionService {

  ResponseEntity<GeneralResponse<Object>> changePassword(ChangePasswordRequest changePasswordRequest);

  ResponseEntity<GeneralResponse<Object>> getInfo();

  ResponseEntity<GeneralResponse<Object>> getAccountByRole(String role);
}
