package com.example.productmoveapi.service.factory;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.AddProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 16:36 on 22/12/2022
 */
public interface FactoryProductManagementService {

  ResponseEntity<GeneralResponse<Object>> addProduct(AddProductRequest addProductRequest);

  ResponseEntity<GeneralResponse<Object>> getProductFromAgency();

  ResponseEntity<GeneralResponse<Object>> addProductToAgency(AddProductListRequest addProductListRequest);

  ResponseEntity<GeneralResponse<Object>> getProductFromWarranty();

  ResponseEntity<GeneralResponse<Object>> addProductFromWarranty(AddProductListRequest addProductListRequest);
}
