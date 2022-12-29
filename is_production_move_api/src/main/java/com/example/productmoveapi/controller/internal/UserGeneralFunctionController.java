package com.example.productmoveapi.controller.internal;

import com.example.productmoveapi.dto.request.user_request.ChangePasswordRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.general.UserGeneralFunctionService;
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

  private final UserGeneralFunctionService userGeneralFunctionService;

  public UserGeneralFunctionController(
      UserGeneralFunctionService userGeneralFunctionService) {
    this.userGeneralFunctionService = userGeneralFunctionService;
  }

  /*
   * @description: User function to get their information
   */
  @GetMapping("/info")
  public ResponseEntity<GeneralResponse<Object>> getInfo() {
    return userGeneralFunctionService.getInfo();
  }

  /*
   * @description: User function to change their password after authentication
   */
  @PostMapping("/change_password")
  public ResponseEntity<GeneralResponse<Object>> changePassword(
      @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
    return userGeneralFunctionService.changePassword(changePasswordRequest);
  }

  /*
   * @description: Get other factory, agency and warranty information
   */
  @GetMapping("/account/{role:^[2-4]*$}")
  public ResponseEntity<GeneralResponse<Object>> getAccountByRole(@PathVariable(name = "role") String role) {
    return userGeneralFunctionService.getAccountByRole(role);
  }
}
