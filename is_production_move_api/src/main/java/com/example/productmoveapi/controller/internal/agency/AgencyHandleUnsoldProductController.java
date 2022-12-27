package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
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
 * @author Binh Nguyen Thai at 22:34 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product/unsold")
@PreAuthorize("hasAuthority('agency')")
public class AgencyHandleUnsoldProductController {

  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyHandleUnsoldProductController(
      AgencyProductManagementService agencyProductManagementService) {
    this.agencyProductManagementService = agencyProductManagementService;
  }

  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductUnsold() {
    return agencyProductManagementService.getProductUnsold();
  }

  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> addProductUnsoldToFactory(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return agencyProductManagementService.addProductUnsoldToFactory(addProductListRequest);
  }
}
