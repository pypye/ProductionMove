package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:16 on 28/12/2022
 */
public interface AgencyImportProductFromFactoryService {

  ResponseEntity<GeneralResponse<Object>> getProductFactory(String factoryId);

  ResponseEntity<GeneralResponse<Object>> addProductFromFactory(String factoryId,
      AddProductListRequest addProductListRequest);
}
