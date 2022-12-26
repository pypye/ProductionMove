package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
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
 * @author Binh Nguyen Thai at 19:23 on 22/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product")
@PreAuthorize("hasAuthority('agency')")
public class AgencyProductManagementController {

  private final AgencyProductManagementService agencyProductManagementService;

  @Autowired
  public AgencyProductManagementController(
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

  @GetMapping("/customer")
  public ResponseEntity<GeneralResponse<Object>> getProductNotCustomer() {
    return agencyProductManagementService.getProductNotCustomer();
  }

  @PostMapping("/customer")
  public ResponseEntity<GeneralResponse<Object>> saleForCustomer(
      @Valid @RequestBody SaleProductRequest saleProductRequest) {
    return agencyProductManagementService.saleForCustomer(saleProductRequest);
  }

  @GetMapping("/{productCode:^[0-9A-Za-z]*$}")
  public ResponseEntity<GeneralResponse<Object>> getProductCustomer(
      @PathVariable(name = "productCode") String productCode) {
    return agencyProductManagementService.getProductCustomer(productCode);
  }

  @PostMapping("/warranty/{productCode:^[0-9A-Za-z]*$}/{warrantyId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> addProductToWarranty(
      @PathVariable(name = "productCode") String productCode, @PathVariable(name = "warrantyId") String warrantyId) {
    return agencyProductManagementService.addProductToWarranty(productCode, warrantyId);
  }

  @GetMapping("/warranty/done")
  public ResponseEntity<GeneralResponse<Object>> getProductFromWarranty() {
    return agencyProductManagementService.getProductFromWarranty();
  }

  @PostMapping("/warranty/done")
  public ResponseEntity<GeneralResponse<Object>> returnProductToCustomer(
      @Valid @RequestBody AddProductListRequest addProductListRequest) {
    return agencyProductManagementService.returnProductToCustomer(addProductListRequest);
  }

  @GetMapping("/warranty/error")
  public ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty() {
    return agencyProductManagementService.getProductErrorFromWarranty();
  }

  @PostMapping("/warranty/error")
  public ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(
      @Valid @RequestBody ChangeProductRequest changeProductRequest) {
    return agencyProductManagementService.changeProductErrorToCustomer(changeProductRequest);
  }

  @PostMapping("/recall/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> recallProduct(@PathVariable(name = "categoryId") String categoryId) {
    return agencyProductManagementService.recallProduct(categoryId);
  }

  @PostMapping("/recall/warranty/{warrantyId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(
      @PathVariable(name = "warrantyId") String warrantyId) {
    return agencyProductManagementService.recallProductToWarranty(warrantyId);
  }
}
