package com.example.productmoveapi.service.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 23:47 on 27/12/2022
 */
public interface FactoryImportNewProductService {

  ResponseEntity<GeneralResponse<Object>> addProduct(AddProductRequest addProductRequest);
}
