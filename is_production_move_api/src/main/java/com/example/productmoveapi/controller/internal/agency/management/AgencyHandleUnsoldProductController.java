package com.example.productmoveapi.controller.internal.agency.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.management.AgencyHandleUnsoldProductService;
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

  private final AgencyHandleUnsoldProductService agencyHandleUnsoldProductService;

  @Autowired
  public AgencyHandleUnsoldProductController(
      AgencyHandleUnsoldProductService agencyHandleUnsoldProductService) {
    this.agencyHandleUnsoldProductService = agencyHandleUnsoldProductService;
  }

  /*
   * @description: Check inventory products from 1 year or more
   */
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductUnsold() {
    return agencyHandleUnsoldProductService.getProductUnsold();
  }

  /*
   * @description: Return to the factory (due to not selling for a long time)
   */
  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> addProductUnsoldToFactory(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return agencyHandleUnsoldProductService.addProductUnsoldToFactory(addProductListRequest);
  }
}
