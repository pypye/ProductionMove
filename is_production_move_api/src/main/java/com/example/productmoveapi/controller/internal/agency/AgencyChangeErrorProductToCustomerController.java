package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
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

  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyChangeErrorProductToCustomerController(
      AgencyProductManagementService agencyProductManagementService) {
    this.agencyProductManagementService = agencyProductManagementService;
  }

  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty() {
    return agencyProductManagementService.getProductErrorFromWarranty();
  }

  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(
      @Valid @RequestBody ChangeProductRequest changeProductRequest) {
    return agencyProductManagementService.changeProductErrorToCustomer(changeProductRequest);
  }
}
