package com.example.productmoveapi.service.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 23:48 on 27/12/2022
 */
public interface FactoryReceiveErrorProductFromWarrantyCenterService {

  /*
   * @description: Get information about error products from the warranty center
   */
  ResponseEntity<GeneralResponse<Object>> getProductFromWarranty();

  /*
   * @description: Receive error products from warranty centers.
   */
  ResponseEntity<GeneralResponse<Object>> addProductFromWarranty(AddProductListRequest addProductListRequest);
}
