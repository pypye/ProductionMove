package com.example.productmoveapi.controller.internal;

import com.example.productmoveapi.dto.request.user_request.ChangePasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.UserInternalService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 13:19 on 14/12/2022
 */

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserGeneralFunctionController {

  private final UserInternalService userInternalService;

  public UserGeneralFunctionController(
      UserInternalService userInternalService) {
    this.userInternalService = userInternalService;
  }

  @GetMapping("/info")
  public ResponseEntity<GeneralResponse<Object>> getInfo() {
    return userInternalService.getInfo();
  }

  @PostMapping("/change_password")
  public ResponseEntity<GeneralResponse<Object>> changePassword(
      @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
    return userInternalService.changePassword(changePasswordRequest);
  }

  @GetMapping("/account/{role:^[2-4]*$}")
  public ResponseEntity<GeneralResponse<Object>> getAccountByRole(@PathVariable(name = "role") String role) {
    return userInternalService.getAccountByRole(role);
  }
}
