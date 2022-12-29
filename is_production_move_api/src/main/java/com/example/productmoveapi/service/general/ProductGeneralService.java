package com.example.productmoveapi.service.general;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 12:06 on 21/12/2022
 */
public interface ProductGeneralService {

  ResponseEntity<GeneralResponse<Object>> getAllCategory();

  ResponseEntity<GeneralResponse<Object>> getAllProduct();

  ResponseEntity<GeneralResponse<Object>> getAllProductByCategory(String categoryId);

}
