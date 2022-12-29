package com.example.productmoveapi.controller.internal.admin.management;

import com.example.productmoveapi.dto.request.user_request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.user_request.UpdateAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.admin.management.AdminUserManagementService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 17:02 on 10/12/2022
 */

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/admin/user")
@PreAuthorize("hasAuthority('admin')")
public class AdminUserManagementController {

  private final AdminUserManagementService adminUserManagementService;

  @Autowired
  public AdminUserManagementController(
      AdminUserManagementService adminUserManagementService) {
    this.adminUserManagementService = adminUserManagementService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<GeneralResponse<Object>> signUp(
      @Valid @RequestBody CreateAccountRequest createAccountRequest) {
    return adminUserManagementService.signupAccount(createAccountRequest);
  }

  @GetMapping("/all")
  public ResponseEntity<GeneralResponse<Object>> getAllAccount() {
    return adminUserManagementService.getAllAccount();
  }

  @DeleteMapping("/delete/{userId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> deleteAccount(
      @PathVariable(name = "userId") String userId) {
    return adminUserManagementService.deleteAccount(userId);
  }

  @PutMapping("/update/{userId:^[0-9]*$}")
  public ResponseEntity<GeneralResponse<Object>> updateAccount(
      @PathVariable(name = "userId") String userId,
      @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {
    return adminUserManagementService.updateAccount(userId, updateAccountRequest);
  }
}
