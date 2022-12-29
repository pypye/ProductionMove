package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:25 on 28/12/2022
 */
public interface AgencyReceiveWarrantyProductFromCustomerService {

  /*
   * @description: Get customer product information and check warranty period
   */
  ResponseEntity<GeneralResponse<Object>> getProductCustomer(String productCode);

  /*
   * @description: Agency send products to warranty center
   */
  ResponseEntity<GeneralResponse<Object>> addProductToWarranty(String productCode, String warrantyId);
}
