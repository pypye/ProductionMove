package com.example.productmoveapi.controller.internal.agency.management;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.management.AgencyReceiveWarrantyProductFromCustomerService;
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

  private final AgencyReceiveWarrantyProductFromCustomerService agencyReceiveWarrantyProductFromCustomerService;

  @Autowired
  public AgencyReceiveWarrantyProductFromCustomerController(
      AgencyReceiveWarrantyProductFromCustomerService agencyReceiveWarrantyProductFromCustomerService) {
    this.agencyReceiveWarrantyProductFromCustomerService = agencyReceiveWarrantyProductFromCustomerService;
  }

  /*
   * @description: Get customer product information and check warranty period
   */
  @GetMapping("/{productCode:^[0-9A-Za-z]*$}")
  public ResponseEntity<GeneralResponse<Object>> getProductCustomer(
      @PathVariable(name = "productCode") String productCode) {
    return agencyReceiveWarrantyProductFromCustomerService.getProductCustomer(productCode);
  }

  /*
   * @description: Agency send products to warranty center
   */
  @PostMapping("/warranty/{productCode:^[0-9A-Za-z]*$}/{warrantyId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> addProductToWarranty(
      @PathVariable(name = "productCode") String productCode, @PathVariable(name = "warrantyId") String warrantyId) {
    return agencyReceiveWarrantyProductFromCustomerService.addProductToWarranty(productCode, warrantyId);
  }

}
