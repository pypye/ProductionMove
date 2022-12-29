package com.example.productmoveapi.controller.internal.agency.management;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.management.AgencyRecallProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:33 on 27/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product/recall")
@PreAuthorize("hasAuthority('agency')")
public class AgencyRecallProductController {

  private final AgencyRecallProductService agencyRecallProductService;

  @Autowired
  public AgencyRecallProductController(
      AgencyRecallProductService agencyRecallProductService) {
    this.agencyRecallProductService = agencyRecallProductService;
  }

  /*
   * @description: Recall a category product
   */
  @PostMapping("/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> recallProduct(@PathVariable(name = "categoryId") String categoryId) {
    return agencyRecallProductService.recallProduct(categoryId);
  }

  /*
   * @description: Bring the recalled product for warranty
   */
  @PostMapping("/warranty/{warrantyId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(
      @PathVariable(name = "warrantyId") String warrantyId) {
    return agencyRecallProductService.recallProductToWarranty(warrantyId);
  }
}
