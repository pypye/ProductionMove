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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:25 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product")
@PreAuthorize("hasAuthority('agency')")
public class AgencyImportProductFromFactoryController {

  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyImportProductFromFactoryController(
      AgencyProductManagementService agencyProductManagementService) {
    this.agencyProductManagementService = agencyProductManagementService;
  }

  @GetMapping("/factory/{factoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> getProductFactory(@PathVariable(name = "factoryId") String factoryId) {
    return agencyProductManagementService.getProductFactory(factoryId);
  }

  @PostMapping("/factory/{factoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> addProductFromFactory(
      @PathVariable(name = "factoryId") String factoryId,
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return agencyProductManagementService.addProductFromFactory(factoryId, addProductListRequest);
  }
}
