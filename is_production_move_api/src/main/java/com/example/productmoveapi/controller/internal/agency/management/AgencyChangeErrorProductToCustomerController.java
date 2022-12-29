package com.example.productmoveapi.controller.internal.agency.management;

import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.management.AgencyChangeErrorProductToCustomerService;
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
 * @author Binh Nguyen Thai at 22:32 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product/warranty/error")
@PreAuthorize("hasAuthority('agency')")
public class AgencyChangeErrorProductToCustomerController {

  private final AgencyChangeErrorProductToCustomerService agencyChangeErrorProductToCustomerService;

  @Autowired
  public AgencyChangeErrorProductToCustomerController(
      AgencyChangeErrorProductToCustomerService agencyChangeErrorProductToCustomerService) {
    this.agencyChangeErrorProductToCustomerService = agencyChangeErrorProductToCustomerService;
  }

  /*
   * @description: Agency receive product error messages from the warranty center
   */
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty() {
    return agencyChangeErrorProductToCustomerService.getProductErrorFromWarranty();
  }

  /*
   * @description: agency exchange error product for customers
   */
  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(
      @Valid @RequestBody ChangeProductRequest changeProductRequest) {
    return agencyChangeErrorProductToCustomerService.changeProductErrorToCustomer(changeProductRequest);
  }
}
