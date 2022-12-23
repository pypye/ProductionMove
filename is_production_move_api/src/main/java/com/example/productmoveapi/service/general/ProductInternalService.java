package com.example.productmoveapi.service.general;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 12:06 on 21/12/2022
 */
public interface ProductInternalService {

  ResponseEntity<GeneralResponse<Object>> getAllCategory();

  ResponseEntity<GeneralResponse<Object>> getAccountByRole(String role);

  ResponseEntity<GeneralResponse<Object>> getAllProduct();

}
