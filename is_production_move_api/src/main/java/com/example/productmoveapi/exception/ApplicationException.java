package com.example.productmoveapi.exception;

import com.example.productmoveapi.response.ResponseStatusEnum;
import java.io.Serial;
import lombok.Getter;

/**
 * @author Binh Nguyen Thai at 22:39 on 28/11/2022
 */

@Getter
public class ApplicationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -578756703743877447L;

  private final ResponseStatusEnum responseStatusEnum;

  // The message is used when you have a detail message which is difference code
  // The message is only used to log, is wont show to end user

  public ApplicationException(ResponseStatusEnum responseStatusEnum) {
    this.responseStatusEnum = responseStatusEnum;
  }
}
