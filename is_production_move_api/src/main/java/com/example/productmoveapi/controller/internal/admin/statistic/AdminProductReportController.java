package com.example.productmoveapi.controller.internal.admin.statistic;

import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.admin.statistic.AdminProductReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 22:31 on 29/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/admin/product/statistic")
@PreAuthorize("hasAuthority('admin')")
public class AdminProductReportController {

  private final AdminProductReportService adminProductReportService;

  @Autowired
  public AdminProductReportController(
      AdminProductReportService adminProductReportService) {
    this.adminProductReportService = adminProductReportService;
  }

  /*
   * @description: Track and view product statistics nationwide, by state and by factory, agency, warranty center.
   */
  @GetMapping("/{statusId}/{roleId}")
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusAndRole(
      @PathVariable(name = "statusId") String statusId, @PathVariable(name = "roleId") String roleId) {
    return adminProductReportService.getProductByStatusAndRole(statusId, roleId);
  }
}
