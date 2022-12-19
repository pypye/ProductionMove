package com.example.productmoveapi.service.implement;

import com.example.productmoveapi.dto.request.ChangePasswordRequest;
import com.example.productmoveapi.dto.response.UserInfoResponse;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.UserInternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 13:21 on 14/12/2022
 */
@Service
@Slf4j
public class UserInternalServiceImplement implements UserInternalService {

  private final ApplicationUserRepository applicationUserRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserInternalServiceImplement(
      ApplicationUserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getInfo() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String username = userDetails.getUsername();
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
    UserInfoResponse userInfoResponse = new UserInfoResponse(applicationUser.getId(),
        applicationUser.getUsername(), applicationUser.getEmail(),
        applicationUser.getRole().getRole());
    return ResponseFactory.success(userInfoResponse);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> changePassword(
      ChangePasswordRequest changePasswordRequest) {
    if (!changePasswordRequest.getRetypePassword().equals(changePasswordRequest.getNewPassword())) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String username = userDetails.getUsername();
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
    if (new BCryptPasswordEncoder().matches(changePasswordRequest.getOldPassword(),
        applicationUser.getPassword())) {
      applicationUser.setPassword(
          bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
      applicationUserRepository.save(applicationUser);
      return ResponseFactory.success("Password has changed !");
    }
    return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.WRONG_INFORMATION);
  }
}
