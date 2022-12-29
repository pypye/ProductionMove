package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:40 on 28/12/2022
 */
public interface AgencyHandleUnsoldProductService {

  /*
   * @description: Check inventory products from 1 year or more
   */
  ResponseEntity<GeneralResponse<Object>> getProductUnsold();

  /*
   * @description: Return to the factory (due to not selling for a long time)
   */
  ResponseEntity<GeneralResponse<Object>> addProductUnsoldToFactory(AddProductListRequest addProductListRequest);
}
