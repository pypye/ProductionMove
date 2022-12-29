package com.example.productmoveapi.controller.internal.factory.management;

import com.example.productmoveapi.dto.request.product_request.AddProductRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.management.FactoryImportNewProductService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:00 on 27/12/2022
 */

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/product")
@PreAuthorize("hasAuthority('factory')")
public class FactoryImportNewProductController {

  private final FactoryImportNewProductService factoryImportNewProductService;

  @Autowired
  public FactoryImportNewProductController(
      FactoryImportNewProductService factoryImportNewProductService) {
    this.factoryImportNewProductService = factoryImportNewProductService;
  }

  /*
   * @description: Enter the new batch of products just produced into the warehouse.
   */
  @PostMapping("/add")
  public ResponseEntity<GeneralResponse<Object>> addProduct(
      @Valid @RequestBody AddProductRequest addProductRequest) {
    return factoryImportNewProductService.addProduct(addProductRequest);
  }
}
