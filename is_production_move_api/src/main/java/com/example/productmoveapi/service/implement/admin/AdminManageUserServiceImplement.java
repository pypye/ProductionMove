package com.example.productmoveapi.service.implement.admin;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.example.productmoveapi.dto.request.UpdateAccountRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.RoleRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Role;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.AdminManageUserService;
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
public class AdminManageUserServiceImplement implements AdminManageUserService {

  private final ApplicationUserRepository applicationUserRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final RoleRepository roleRepository;

  @Autowired
  public AdminManageUserServiceImplement(
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

    if (applicationUserRepository.findByUsername(updateAccountRequest.getUsername()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_USERNAME);
    }

    if (applicationUserRepository.findByEmail(updateAccountRequest.getEmail()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.REGISTERED_EMAIL);
    }

    applicationUser.setUsername(updateAccountRequest.getUsername());
    applicationUser.setPassword(bCryptPasswordEncoder.encode(updateAccountRequest.getPassword()));
    applicationUser.setEmail(updateAccountRequest.getEmail());
    applicationUser.setRole(roleRepository.findRoleById(updateAccountRequest.getRole_id()));
    applicationUserRepository.save(applicationUser);
    return ResponseFactory.success("update succesfully!");
  }
}
