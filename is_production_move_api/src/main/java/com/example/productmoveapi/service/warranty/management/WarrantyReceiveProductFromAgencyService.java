package com.example.productmoveapi.service.warranty.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:05 on 28/12/2022
 */
public interface WarrantyReceiveProductFromAgencyService {

  ResponseEntity<GeneralResponse<Object>> getProductFromAgency();

  ResponseEntity<GeneralResponse<Object>> addProductFromAgency(AddProductListRequest addProductListRequest);
}
