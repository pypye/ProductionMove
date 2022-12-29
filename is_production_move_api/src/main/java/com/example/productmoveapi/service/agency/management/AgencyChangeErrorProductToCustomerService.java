package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:32 on 28/12/2022
 */
public interface AgencyChangeErrorProductToCustomerService {

  ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty();

  ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(ChangeProductRequest changeProductRequest);
}
