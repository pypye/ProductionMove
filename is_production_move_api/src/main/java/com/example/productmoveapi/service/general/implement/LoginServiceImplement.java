package com.example.productmoveapi.service.general.implement;

import com.example.productmoveapi.dto.request.user_request.ForgotPasswordRequest;
import com.example.productmoveapi.dto.request.user_request.LoginRequest;
import com.example.productmoveapi.dto.request.user_request.ResetPasswordRequest;
import com.example.productmoveapi.dto.response.LoginResponse;
import com.example.productmoveapi.mail_sender.EmailService;
import com.example.productmoveapi.redis.service.IRedisCaching;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.security.filter.JWT.JwtUtils;
import com.example.productmoveapi.security.filter.service.UserDetailsImplement;
import com.example.productmoveapi.service.general.LoginService;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 22:57 on 28/11/2022
 */

@Service
@Slf4j
public class LoginServiceImplement implements LoginService {

  @Value("${spring.mail.username}")
  private String username;

  @Value("${token.ttl}")
  private Long ttl;

  private final ApplicationUserRepository applicationUserRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final IRedisCaching iRedisCaching;

  private final AuthenticationManager authenticationManager;

  private final JwtUtils jwtUtils;

  private final EmailService emailService;

  @Autowired
  public LoginServiceImplement(ApplicationUserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      IRedisCaching iRedisCaching, AuthenticationManager authenticationManager, JwtUtils jwtUtils,
      EmailService emailService) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.iRedisCaching = iRedisCaching;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.emailService = emailService;
  }

  private ResponseEntity<GeneralResponse<Object>> checkAccount(LoginRequest loginRequest) {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(
        loginRequest.getUsername());
    if (applicationUser != null) {
      if (new BCryptPasswordEncoder().matches(loginRequest.getPassword(),
          applicationUser.getPassword())) {
        return null;
      }
    }
    return ResponseFactory.error(HttpStatus.valueOf(403),
        ResponseStatusEnum.WRONG_INFORMATION);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> loginAccount(LoginRequest loginRequest) {
    ResponseEntity<GeneralResponse<Object>> loginResult = checkAccount(loginRequest);
    if (loginResult != null) {
      return loginResult;
    }
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJWT(authentication);
    UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();
    LoginResponse loginResponse = new LoginResponse(
        jwt,
        userDetailsImplement.getId(),
        userDetailsImplement.getUsername(),
        userDetailsImplement.getEmail(),
        userDetailsImplement.getAuthorities().iterator().next().toString()
    );
    return ResponseFactory.success(loginResponse, LoginResponse.class);
  }

  public static String getRandomNumberString() {
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    return String.format("%06d", number);
  }

  private void sendMail(String mail, String token) {
    CompletableFuture.runAsync(() ->
        emailService.sendEmail(username,
            mail,
            "Password Reset Request",
            "Type this verify code on our app:\n" + token)
    );
    log.info("Mail has been sent!");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
    ApplicationUser applicationUser = applicationUserRepository.findByEmail(
        forgotPasswordRequest.getEmail());
    if (applicationUser == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    String token = getRandomNumberString();
    iRedisCaching.addOpsValue(token, applicationUser.getId(), ttl);
    sendMail(applicationUser.getEmail(), token);
    return ResponseFactory.success("A reset token has been sent to " + applicationUser.getEmail());
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> resetPassword(
      ResetPasswordRequest resetPasswordRequest) {
    String token = resetPasswordRequest.getToken();
    String userId = (String) iRedisCaching.getFromOpsValue(token);
    if (userId == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    if (!resetPasswordRequest.getRetypePassword().equals(resetPasswordRequest.getPassword())) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    ApplicationUser applicationUser = applicationUserRepository.findById(userId).orElse(null);
    if (applicationUser == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    applicationUser.setPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getPassword()));
    applicationUserRepository.save(applicationUser);
    iRedisCaching.removeFromOpsValue(token);
    return ResponseFactory.success("Password has changed !");
  }

}
