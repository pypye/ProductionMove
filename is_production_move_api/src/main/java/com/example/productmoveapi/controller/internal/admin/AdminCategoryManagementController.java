package com.example.productmoveapi.controller.internal.admin;

import com.example.productmoveapi.dto.request.category_request.CategoryRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.admin.AdminCategoryManagementService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 11:59 on 21/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/admin/category")
@PreAuthorize("hasAuthority('admin')")
public class AdminCategoryManagementController {

  private final AdminCategoryManagementService adminCategoryManagementService;

  @Autowired
  public AdminCategoryManagementController(
      AdminCategoryManagementService adminCategoryManagementService) {
    this.adminCategoryManagementService = adminCategoryManagementService;
  }

  @PostMapping("/add")
  public ResponseEntity<GeneralResponse<Object>> addCategory(
      @Valid @RequestBody CategoryRequest categoryRequest) {
    return adminCategoryManagementService.addCategory(categoryRequest);
  }

  @DeleteMapping("/delete/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> deleteCategory(
      @PathVariable(name = "categoryId") String categoryId) {
    return adminCategoryManagementService.deleteCategory(categoryId);
  }

  @PutMapping("/update/{categoryId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> updateCategory(
      @PathVariable(name = "categoryId") String categoryId,
      @Valid @RequestBody CategoryRequest categoryRequest) {
    return adminCategoryManagementService.updateCategory(categoryId, categoryRequest);
  }

}
