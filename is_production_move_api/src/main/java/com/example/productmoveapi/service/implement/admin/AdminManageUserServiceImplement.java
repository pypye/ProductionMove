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

}
