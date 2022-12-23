package com.example.productmoveapi.controller.internal.factory;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.AddProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.FactoryProductManagementService;
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
 * @author Binh Nguyen Thai at 16:30 on 22/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/product")
@PreAuthorize("hasAuthority('factory')")
public class FactoryProductManagementController {

  private final FactoryProductManagementService factoryProductManagementService;

  @Autowired
  public FactoryProductManagementController(
      FactoryProductManagementService factoryProductManagementService) {
    this.factoryProductManagementService = factoryProductManagementService;
  }

  @PostMapping("/add")
  public ResponseEntity<GeneralResponse<Object>> addProduct(
      @Valid @RequestBody AddProductRequest addProductRequest) {
    return factoryProductManagementService.addProduct(addProductRequest);
  }

  @GetMapping("/agency")
  public ResponseEntity<GeneralResponse<Object>> getProductFromAgency() {
    return factoryProductManagementService.getProductFromAgency();
  }

  @PostMapping("/agency")
  public ResponseEntity<GeneralResponse<Object>> addProductToAgency(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return factoryProductManagementService.addProductToAgency(addProductListRequest);
  }
}
