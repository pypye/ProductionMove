package com.example.productmoveapi.controller.internal.warranty;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.warranty.WarrantyProductManagementService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:11 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/warranty/product/agency")
@PreAuthorize("hasAuthority('warranty')")
public class WarrantyReceiveProductFromAgencyController {

  private final WarrantyProductManagementService warrantyProductManagementService;

  @Autowired
  public WarrantyReceiveProductFromAgencyController(
      WarrantyProductManagementService warrantyProductManagementService) {
    this.warrantyProductManagementService = warrantyProductManagementService;
  }

  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductFromAgency() {
    return warrantyProductManagementService.getProductFromAgency();
  }

  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> addProductFromAgency(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return warrantyProductManagementService.addProductFromAgency(addProductListRequest);
  }
}
