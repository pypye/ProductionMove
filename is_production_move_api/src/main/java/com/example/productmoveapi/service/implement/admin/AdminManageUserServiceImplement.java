package com.example.productmoveapi.service.implement.admin;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.RoleRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Role;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.AdminManageUserService;
import com.example.productmoveapi.service.valid.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 17:06 on 10/12/2022
 */
@Service
@Slf4j
public class AdminManageUserServiceImplement implements AdminManageUserService {

  private final ApplicationUserRepository applicationUserRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final ValidationService validationService;

  private final RoleRepository roleRepository;

  @Autowired
  public AdminManageUserServiceImplement(
      ApplicationUserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      ValidationService validationService,
      RoleRepository roleRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.validationService = validationService;
    this.roleRepository = roleRepository;
  }

  private ResponseEntity<GeneralResponse<Object>> validateSignUp(
      CreateAccountRequest createAccountRequest) {
    if (!validationService.checkRetypePassword(createAccountRequest.getRetypePassword(),
        createAccountRequest.getPassword())) {
      return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.RETYPE_ERROR);
    }
    return validationService.validRequest(
        createAccountRequest.getUsername(),
        createAccountRequest.getPassword(),
        createAccountRequest.getEmail());
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> signupAccount(
      CreateAccountRequest createAccountRequest) {
    ResponseEntity<GeneralResponse<Object>> validateResult = validateSignUp(createAccountRequest);
    if (validateResult != null) {
      return validateResult;
    }
    validateResult = validationService.validExist(createAccountRequest.getUsername(),
        createAccountRequest.getEmail());
    if (validateResult != null) {
      return validateResult;
    }

    createAccountRequest.setPassword(
        bCryptPasswordEncoder.encode(createAccountRequest.getPassword()));
    ApplicationUser applicationUser = new ApplicationUser();
    Role role = roleRepository.findRoleById(createAccountRequest.getRole_id());
    applicationUser.setCreateAccountRequest(createAccountRequest, role);
    applicationUserRepository.save(applicationUser);
    return ResponseFactory.success(applicationUser);
  }

//  @Override
//  public ResponseEntity<GeneralResponse<Object>> changePassword(
//      ChangePasswordRequest changePasswordRequest) {
//    ResponseEntity<GeneralResponse<Object>> validateResult = validationService.validateChangePassword(changePasswordRequest.getNewPassword(), changePasswordRequest.getRetypePassword());
//    if (validateResult != null) return validateResult;
//    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = userDetails.getUsername();
//    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
//    if (new BCryptPasswordEncoder().matches(changePasswordRequest.getOldPassword(), applicationUser.getPassword())) {
//      applicationUser.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
//      applicationUserRepository.save(applicationUser);
//      return ResponseFactory.success("Password has changed !");
//    }
//    return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.RETYPE_OLD_PASSWORD_ERROR);
//  }

//  @Override
//  public ResponseEntity<GeneralResponse<Object>> verifySignUp(
//      VerifyAccountRequest verifyAccountRequest){
//    String token = verifyAccountRequest.getToken();
//    String userId = (String) iRedisCaching.getFromOpsValue(token);
//    if (userId == null)
//      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.UNKNOWN_ERROR);
//    ApplicationUser applicationUser = applicationUserRepository.findById(userId).orElse(null);
//    if (applicationUser == null) return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.NOT_EXIST);
//    applicationUser.setStatus(true);
//    applicationUserRepository.save(applicationUser);
//    iRedisCaching.removeFromOpsValue(token);
//    return ResponseFactory.success(applicationUser);
//  }

//  @Override
//  public ResponseEntity<GeneralResponse<Object>> logoutAccount(){
//    return ResponseFactory.success("Logout successfully!");
//  }
}
