package com.example.productmoveapi.controller.internal;

import com.example.productmoveapi.dto.request.static_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.ProductReportGeneralService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 15:29 on 21/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
public class ProductReportGeneralController {

  private final ProductReportGeneralService productReportGeneralService;

  @Autowired
  public ProductReportGeneralController(
      ProductReportGeneralService productReportGeneralService) {
    this.productReportGeneralService = productReportGeneralService;
  }

  @GetMapping("/category/all")
  public ResponseEntity<GeneralResponse<Object>> getAllCategory() {
    return productReportGeneralService.getAllCategory();
  }

  @GetMapping("/product/all")
  public ResponseEntity<GeneralResponse<Object>> getAllProduct() {
    return productReportGeneralService.getAllProduct();
  }

  @GetMapping("/product/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> getAllProductByCategory(
      @PathVariable(name = "categoryId") String categoryId) {
    return productReportGeneralService.getAllProductByCategory(categoryId);
  }

  @GetMapping("/static")
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      @Valid @RequestBody StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest) {
    return productReportGeneralService.getProductByStatusYearQuarterMonth(staticByStatusYearQuarterMonthRequest);
  }
}
