package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.dto.request.product_request.AddProductFromFactoryRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 19:24 on 22/12/2022
 */
public interface AgencyProductManagementService {

  ResponseEntity<GeneralResponse<Object>> getProductFactory(String factoryId);

  ResponseEntity<GeneralResponse<Object>> addProductFromFactory(String factoryId,
      AddProductFromFactoryRequest addProductFromFactoryRequest);
}
