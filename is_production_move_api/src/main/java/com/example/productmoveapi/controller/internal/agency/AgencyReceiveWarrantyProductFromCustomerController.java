package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:27 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product")
@PreAuthorize("hasAuthority('agency')")
public class AgencyReceiveWarrantyProductFromCustomerController {

  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyReceiveWarrantyProductFromCustomerController(
      AgencyProductManagementService agencyProductManagementService) {
    this.agencyProductManagementService = agencyProductManagementService;
  }

  @GetMapping("/{productCode:^[0-9A-Za-z]*$}")
  public ResponseEntity<GeneralResponse<Object>> getProductCustomer(
      @PathVariable(name = "productCode") String productCode) {
    return agencyProductManagementService.getProductCustomer(productCode);
  }

  @PostMapping("/warranty/{productCode:^[0-9A-Za-z]*$}/{warrantyId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> addProductToWarranty(
      @PathVariable(name = "productCode") String productCode, @PathVariable(name = "warrantyId") String warrantyId) {
    return agencyProductManagementService.addProductToWarranty(productCode, warrantyId);
  }

}
