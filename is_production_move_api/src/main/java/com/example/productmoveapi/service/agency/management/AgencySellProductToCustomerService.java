package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:20 on 28/12/2022
 */
public interface AgencySellProductToCustomerService {

  ResponseEntity<GeneralResponse<Object>> getProductNotCustomer();

  ResponseEntity<GeneralResponse<Object>> saleForCustomer(SaleProductRequest saleProductRequest);
}
