package com.example.productmoveapi.service.factory;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 23:47 on 27/12/2022
 */
public interface FactoryExportProductToAgencyService {

  ResponseEntity<GeneralResponse<Object>> getProductFromAgency();

  ResponseEntity<GeneralResponse<Object>> addProductToAgency(AddProductListRequest addProductListRequest);
}
