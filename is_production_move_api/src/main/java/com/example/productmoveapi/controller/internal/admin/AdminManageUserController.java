package com.example.productmoveapi.controller.internal.admin;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.AdminManageUserService;
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
 * @author Binh Nguyen Thai at 17:02 on 10/12/2022
 */

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/admin/user")
@PreAuthorize("hasAuthority('admin')")
public class AdminManageUserController {

  private final AdminManageUserService adminManageUserService;

  @Autowired
  public AdminManageUserController(
      AdminManageUserService adminManageUserService) {
    this.adminManageUserService = adminManageUserService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<GeneralResponse<Object>> signUp(
      @Valid @RequestBody CreateAccountRequest createAccountRequest) {
    return adminManageUserService.signupAccount(createAccountRequest);
  }

}
