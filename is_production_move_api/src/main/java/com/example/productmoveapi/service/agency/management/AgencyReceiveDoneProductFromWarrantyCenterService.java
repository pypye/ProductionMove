package com.example.productmoveapi.service.agency.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:29 on 28/12/2022
 */
public interface AgencyReceiveDoneProductFromWarrantyCenterService {

  ResponseEntity<GeneralResponse<Object>> getProductFromWarranty();

  ResponseEntity<GeneralResponse<Object>> returnProductToCustomer(AddProductListRequest addProductListRequest);
}
