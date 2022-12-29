package com.example.productmoveapi.service.warranty.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:09 on 28/12/2022
 */
public interface WarrantyReturnProductService {

  /*
   * @description: Get information about the products in the warranty center
   */
  ResponseEntity<GeneralResponse<Object>> getProductInWarranty();

  /*
   * @description: Return the repaired product to the agency.
   */
  ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(AddProductListRequest addProductListRequest);

  /*
   * @description: Return the product that cannot be repaired to the factory
   */
  ResponseEntity<GeneralResponse<Object>> addProductErrorToFactory(AddProductListRequest addProductListRequest,
      String factoryId);
}
