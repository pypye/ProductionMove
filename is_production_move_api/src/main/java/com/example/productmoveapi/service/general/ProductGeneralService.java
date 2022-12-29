package com.example.productmoveapi.service.general;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 12:06 on 21/12/2022
 */
public interface ProductGeneralService {

  ResponseEntity<GeneralResponse<Object>> getAllCategory();

  /*
   * @description: Get information of all current user's products
   */
  ResponseEntity<GeneralResponse<Object>> getAllProduct();

  /*
   * @description: Get information of all current user's products by category
   */
  ResponseEntity<GeneralResponse<Object>> getAllProductByCategory(String categoryId);

}
