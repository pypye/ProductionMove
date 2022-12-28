package com.example.productmoveapi.exception;

import java.util.Date;
import lombok.Getter;

/**
 * @author Binh Nguyen Thai at 22:37 on 28/11/2022
 */

@Getter
public class ErrorMessage {

  private final int statusCode;
  private final Date timestamp;
  private final String message;
  private final String description;

  public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }

  public String getMessage() {
    return message;
  }

}