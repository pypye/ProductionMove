package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 19:23 on 22/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product")
@PreAuthorize("hasAuthority('agency')")
public class AgencyProductManagementController {
  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyProductManagementController(
      AgencyProductManagementService agencyProductManagementService) {
    this.agencyProductManagementService = agencyProductManagementService;
  }

  @GetMapping("/factory")
  public ResponseEntity<GeneralResponse<Object>> getProductFactory() {
    return agencyProductManagementService.getProductFactory();
  }

//  @PostMapping("/add")
//  public ResponseEntity<GeneralResponse<Object>> addProduct(
//      @Valid @RequestBody AddProductRequest addProductRequest) {
//    return factoryProductManagementService.addProduct(addProductRequest);
//  }
}
