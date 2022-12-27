package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
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

  ResponseEntity<GeneralResponse<Object>> getProductNotCustomer();

  ResponseEntity<GeneralResponse<Object>> saleForCustomer(SaleProductRequest saleProductRequest);

  ResponseEntity<GeneralResponse<Object>> getProductCustomer(String productCode);

  ResponseEntity<GeneralResponse<Object>> addProductToWarranty(String productCode, String warrantyId);

  ResponseEntity<GeneralResponse<Object>> getProductFromWarranty();

  ResponseEntity<GeneralResponse<Object>> returnProductToCustomer(AddProductListRequest addProductListRequest);

  ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty();

  ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(ChangeProductRequest changeProductRequest);

  ResponseEntity<GeneralResponse<Object>> recallProduct(String categoryId);

  ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(String warrantyId);

  ResponseEntity<GeneralResponse<Object>> getProductUnsold();

  ResponseEntity<GeneralResponse<Object>> addProductUnsoldToFactory(AddProductListRequest addProductListRequest);
}
