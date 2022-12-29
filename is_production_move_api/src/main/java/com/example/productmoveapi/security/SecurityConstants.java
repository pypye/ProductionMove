package com.example.productmoveapi.security;

/**
 * @author Binh Nguyen Thai at 10:16 on 05/12/2022
 */
public class SecurityConstants {

  /*
   * @description: some param for creating jwt and authorization
   */
  public static final String SECRET = "NguyenThaiBinhTranVanDuc";
  public static final long EXPIRATION_TIME = 86400_000;
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String AUTH = "/auth/*";

}