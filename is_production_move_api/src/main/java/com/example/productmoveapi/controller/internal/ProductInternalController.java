package com.example.productmoveapi.controller.internal;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.ProductInternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 15:29 on 21/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
public class ProductInternalController {

  private final ProductInternalService productInternalService;

  @Autowired
  public ProductInternalController(
      ProductInternalService productInternalService) {
    this.productInternalService = productInternalService;
  }

  @GetMapping("/category/all")
  public ResponseEntity<GeneralResponse<Object>> getAllCategory() {
    return productInternalService.getAllCategory();
  }

  @GetMapping("/product/all")
  public ResponseEntity<GeneralResponse<Object>> getAllProduct() {
    return productInternalService.getAllProduct();
  }

  @GetMapping("/product/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> getAllProductByCategory(
      @PathVariable(name = "categoryId") String categoryId) {
    return productInternalService.getAllProductByCategory(categoryId);
  }

}
