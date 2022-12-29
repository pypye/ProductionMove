package com.example.productmoveapi.response;

import lombok.Getter;

/**
 * @author Binh Nguyen Thai at 22:32 on 28/11/2022
 */

@Getter
public enum ResponseStatusEnum {

  SUCCESS("SUCCESS", "Request successfully"),
  UNKNOWN_ERROR("E-000", "Can not specify error"),
  NOT_ENOUGH_PARAM("E-001", "Not enough param in request"),
  INVALID_REQUEST_PARAM("E-002",
      "Invalid format param in request"),
  WRONG_INFORMATION("E-003", "Something went wrong"),
  REGISTERED_USERNAME("E-004", "Username is already registered"),
  REGISTERED_EMAIL("E-005", "Email is already registered"),
  REGISTERED_COMPANY_NAME("E-006", "Company name is already registered"),
  EXISTED_CATEGORY("E-007", "Category is already existed"),
  NOT_MATCHING_PRODUCT_FOUND("E-008", "No matching products found");

  private final String code;
  private final String message;

  ResponseStatusEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }
}

