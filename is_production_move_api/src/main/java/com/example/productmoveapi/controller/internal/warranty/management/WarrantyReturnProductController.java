package com.example.productmoveapi.controller.internal.warranty.management;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.warranty.management.WarrantyReturnProductService;
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
 * @author Binh Nguyen Thai at 22:13 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/warranty/product")
@PreAuthorize("hasAuthority('warranty')")
public class WarrantyReturnProductController {

  private final WarrantyReturnProductService warrantyReturnProductService;

  @Autowired
  public WarrantyReturnProductController(
      WarrantyReturnProductService warrantyReturnProductService) {
    this.warrantyReturnProductService = warrantyReturnProductService;
  }

  @GetMapping("/all")
  public ResponseEntity<GeneralResponse<Object>> getProductInWarranty() {
    return warrantyReturnProductService.getProductInWarranty();
  }

  @PostMapping("/agency/done")
  public ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return warrantyReturnProductService.addProductDoneToAgency(addProductListRequest);
  }

  @PostMapping("/factory/error/{factoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> addProductErrorToFactory(
      @Valid @RequestBody AddProductListRequest addProductListRequest,
      @PathVariable(name = "factoryId") String factoryId) {
    return warrantyReturnProductService.addProductErrorToFactory(addProductListRequest, factoryId);
  }
}
