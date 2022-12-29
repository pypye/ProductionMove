package com.example.productmoveapi.controller.internal.agency.management;

import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.management.AgencySellProductToCustomerService;
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
 * @author Binh Nguyen Thai at 22:26 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product/customer")
@PreAuthorize("hasAuthority('agency')")
public class AgencySellProductToCustomerController {

  private final AgencySellProductToCustomerService agencySellProductToCustomerService;

  @Autowired
  public AgencySellProductToCustomerController(
      AgencySellProductToCustomerService agencySellProductToCustomerService) {
    this.agencySellProductToCustomerService = agencySellProductToCustomerService;
  }

  /*
   * @description: Get a list of products available in the agency
   */
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductNotCustomer() {
    return agencySellProductToCustomerService.getProductNotCustomer();
  }

  /*
   * @description: Sell products and save customer information
   */
  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> saleForCustomer(
      @Valid @RequestBody SaleProductRequest saleProductRequest) {
    return agencySellProductToCustomerService.saleForCustomer(saleProductRequest);
  }
}
