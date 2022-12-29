package com.example.productmoveapi.service.warranty.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:09 on 28/12/2022
 */
public interface WarrantyReturnProductService {

  ResponseEntity<GeneralResponse<Object>> getProductInWarranty();

  ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(AddProductListRequest addProductListRequest);

  ResponseEntity<GeneralResponse<Object>> addProductErrorToFactory(AddProductListRequest addProductListRequest,
      String factoryId);
}
