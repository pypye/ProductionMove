package com.example.productmoveapi.service.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 23:48 on 27/12/2022
 */
public interface FactoryReceiveErrorProductFromWarrantyCenterService {

  ResponseEntity<GeneralResponse<Object>> getProductFromWarranty();

  ResponseEntity<GeneralResponse<Object>> addProductFromWarranty(AddProductListRequest addProductListRequest);
}
