package com.example.productmoveapi.service.general;

import com.example.productmoveapi.dto.request.user_request.ChangePasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 13:21 on 14/12/2022
 */
public interface UserGeneralFunctionService {

  /*
   * @description: User function to change their password after authentication
   */
  ResponseEntity<GeneralResponse<Object>> changePassword(ChangePasswordRequest changePasswordRequest);

  /*
   * @description: User function to get their information
   */
  ResponseEntity<GeneralResponse<Object>> getInfo();

  /*
   * @description: Get other factory, agency and warranty information
   */
  ResponseEntity<GeneralResponse<Object>> getAccountByRole(String role);
}
