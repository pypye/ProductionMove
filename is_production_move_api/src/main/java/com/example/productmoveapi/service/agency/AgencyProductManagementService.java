package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 19:24 on 22/12/2022
 */
public interface AgencyProductManagementService {

  ResponseEntity<GeneralResponse<Object>> getProductFactory(String factoryId);

  ResponseEntity<GeneralResponse<Object>> addProductFromFactory(String factoryId,
      AddProductListRequest addProductListRequest);

  ResponseEntity<GeneralResponse<Object>> saleForCustomer(SaleProductRequest saleProductRequest);

  ResponseEntity<GeneralResponse<Object>> getProductCustomer(String productCode);
}
