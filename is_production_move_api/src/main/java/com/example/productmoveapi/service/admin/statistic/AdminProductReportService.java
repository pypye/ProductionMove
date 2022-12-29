package com.example.productmoveapi.service.admin.statistic;

import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 00:41 on 30/12/2022
 */
public interface AdminProductReportService {

  /*
   * @description: Track and view product statistics nationwide, by state and by factory, agency, warranty center.
   */
  ResponseEntity<GeneralResponse<Object>> getProductByStatusAndRole(String statusId, String roleId);
}
