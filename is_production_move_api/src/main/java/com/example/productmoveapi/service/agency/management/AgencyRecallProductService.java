package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:36 on 28/12/2022
 */
public interface AgencyRecallProductService {

  /*
   * @description: Recall a category product
   */
  ResponseEntity<GeneralResponse<Object>> recallProduct(String categoryId);

  /*
   * @description: Bring the recalled product for warranty
   */
  ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(String warrantyId);
}
