package com.example.productmoveapi.controller.internal.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.management.FactoryReceiveErrorProductFromWarrantyCenterService;
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
 * @author Binh Nguyen Thai at 22:03 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/product/warranty")
@PreAuthorize("hasAuthority('factory')")
public class FactoryReceiveErrorProductFromWarrantyCenterController {

  private final FactoryReceiveErrorProductFromWarrantyCenterService factoryReceiveErrorProductFromWarrantyCenterService;

  @Autowired
  public FactoryReceiveErrorProductFromWarrantyCenterController(
      FactoryReceiveErrorProductFromWarrantyCenterService factoryReceiveErrorProductFromWarrantyCenterService) {
    this.factoryReceiveErrorProductFromWarrantyCenterService = factoryReceiveErrorProductFromWarrantyCenterService;
  }

  /*
   * @description: Get information about error products from the warranty center
   */
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getProductFromWarranty() {
    return factoryReceiveErrorProductFromWarrantyCenterService.getProductFromWarranty();
  }

  /*
   * @description: Receive error products from warranty centers.
   */
  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> addProductFromWarranty(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return factoryReceiveErrorProductFromWarrantyCenterService.addProductFromWarranty(addProductListRequest);
  }
}
