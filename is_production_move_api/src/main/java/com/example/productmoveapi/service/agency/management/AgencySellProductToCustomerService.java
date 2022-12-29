package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:20 on 28/12/2022
 */
public interface AgencySellProductToCustomerService {

  /*
   * @description: Get a list of products available in the agency
   */
  ResponseEntity<GeneralResponse<Object>> getProductNotCustomer();

  /*
   * @description: Sell products and save customer information
   */
  ResponseEntity<GeneralResponse<Object>> saleForCustomer(SaleProductRequest saleProductRequest);
}
