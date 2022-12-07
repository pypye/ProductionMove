package com.example.productmoveapi.security;

/**
 * @author Binh Nguyen Thai at 10:16 on 05/12/2022
 */
public class SecurityConstants {
  public static final String SECRET = "NguyenThaiBinhTranVanDuc";
  //public static final long EXPIRATION_TIME = 86400_000;
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/auth/sign-up";
  public static final String LOGIN_URL = "/auth/login";
  public static final String FORGOT_PASSWORD_URL = "/auth/forgot";
  public static final String RESET_PASSWORD_URL = "/auth/reset";
  public static final String VERIFY_URL = "/auth/verify";
  public static final String LOGOUT_URL = "/auth/logout";
}