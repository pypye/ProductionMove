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
  public static final String AUTH = "/auth/*";

}