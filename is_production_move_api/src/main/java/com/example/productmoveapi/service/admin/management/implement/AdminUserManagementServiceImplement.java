package com.example.productmoveapi.service.admin.management.implement;

import com.example.productmoveapi.dto.request.user_request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.user_request.UpdateAccountRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.RoleRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Role;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.admin.management.AdminUserManagementService;
import java.util.List;
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
public class AdminUserManagementServiceImplement implements AdminUserManagementService {

  private final ApplicationUserRepository applicationUserRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final RoleRepository roleRepository;

  @Autowired
  public AdminUserManagementServiceImplement(
      ApplicationUserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      RoleRepository roleRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> signupAccount(
      CreateAccountRequest createAccountRequest) {
    if (!createAccountRequest.getRetypePassword().equals(createAccountRequest.getPassword())) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    if (applicationUserRepository.findByUsername(createAccountRequest.getUsername()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_USERNAME);
    }

    if (applicationUserRepository.findByEmail(createAccountRequest.getEmail()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_EMAIL);
    }

    if (applicationUserRepository.findByCompanyName(createAccountRequest.getCompanyName()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_COMPANY_NAME);
    }

    createAccountRequest.setPassword(
        bCryptPasswordEncoder.encode(createAccountRequest.getPassword()));
    ApplicationUser applicationUser = new ApplicationUser();
    Role role = roleRepository.findRoleById(createAccountRequest.getRole_id());
    applicationUser.setCreateAccountRequest(createAccountRequest, role);
    applicationUserRepository.save(applicationUser);
    return ResponseFactory.success(applicationUser);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllAccount() {
    List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
    return ResponseFactory.success(applicationUserList);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> deleteAccount(String id) {
    ApplicationUser applicationUser = applicationUserRepository.findById(id).orElse(null);
    if (applicationUser == null || applicationUser.getRole().getRole().equals("admin")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    applicationUserRepository.deleteById(id);
    return ResponseFactory.success("delete succesfully!");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> updateAccount(String id, UpdateAccountRequest updateAccountRequest) {
    ApplicationUser applicationUser = applicationUserRepository.findById(id).orElse(null);
    if (applicationUser == null || applicationUser.getRole().getRole().equals("admin")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    if (!applicationUser.getUsername().equals(updateAccountRequest.getUsername())) {
      if (applicationUserRepository.findByUsername(updateAccountRequest.getUsername()) != null) {
        return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_USERNAME);
      }
    }

    if (!applicationUser.getEmail().equals(updateAccountRequest.getEmail())) {
      if (applicationUserRepository.findByEmail(updateAccountRequest.getEmail()) != null) {
        return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_EMAIL);
      }
    }

    if (!applicationUser.getCompanyName().equals(updateAccountRequest.getCompanyName())) {
      if (applicationUserRepository.findByCompanyName(updateAccountRequest.getCompanyName()) != null) {
        return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_COMPANY_NAME);
      }
    }

    applicationUser.setUsername(updateAccountRequest.getUsername());
    applicationUser.setEmail(updateAccountRequest.getEmail());
    applicationUser.setRole(roleRepository.findRoleById(updateAccountRequest.getRole_id()));
    applicationUser.setAddress(updateAccountRequest.getAddress());
    applicationUser.setCompanyName(updateAccountRequest.getCompanyName());
    applicationUser.setPhone(updateAccountRequest.getPhone());
    applicationUserRepository.save(applicationUser);
    return ResponseFactory.success("update succesfully!");
  }
}
