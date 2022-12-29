package com.example.productmoveapi.controller.internal.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.management.FactoryExportProductToAgencyService;
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
 * @author Binh Nguyen Thai at 22:01 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/product/agency")
@PreAuthorize("hasAuthority('factory')")
public class FactoryExportProductToAgencyController {

  private final FactoryExportProductToAgencyService factoryExportProductToAgencyService;

  @Autowired
  public FactoryExportProductToAgencyController(
      FactoryExportProductToAgencyService factoryExportProductToAgencyService) {
    this.factoryExportProductToAgencyService = factoryExportProductToAgencyService;
  }

  /*
   * @description: Receive product production requests from agency
   */
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductFromAgency() {
    return factoryExportProductToAgencyService.getProductFromAgency();
  }

  /*
   * @description: Export product to agency
   */
  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> addProductToAgency(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return factoryExportProductToAgencyService.addProductToAgency(addProductListRequest);
  }
}
