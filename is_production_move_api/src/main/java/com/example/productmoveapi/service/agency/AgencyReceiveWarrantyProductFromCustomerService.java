package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:25 on 28/12/2022
 */
public interface AgencyReceiveWarrantyProductFromCustomerService {

  ResponseEntity<GeneralResponse<Object>> getProductCustomer(String productCode);

  ResponseEntity<GeneralResponse<Object>> addProductToWarranty(String productCode, String warrantyId);
}
