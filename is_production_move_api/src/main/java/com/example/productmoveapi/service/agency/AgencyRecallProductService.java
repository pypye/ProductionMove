package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:36 on 28/12/2022
 */
public interface AgencyRecallProductService {

  ResponseEntity<GeneralResponse<Object>> recallProduct(String categoryId);

  ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(String warrantyId);
}
