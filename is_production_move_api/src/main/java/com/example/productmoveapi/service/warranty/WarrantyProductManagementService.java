package com.example.productmoveapi.service.warranty;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 15:31 on 24/12/2022
 */
public interface WarrantyProductManagementService {

  ResponseEntity<GeneralResponse<Object>> getProductFromAgency();

  ResponseEntity<GeneralResponse<Object>> addProductFromAgency(AddProductListRequest addProductListRequest);

  ResponseEntity<GeneralResponse<Object>> getProductInWarranty();

  ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(AddProductListRequest addProductListRequest);
}
