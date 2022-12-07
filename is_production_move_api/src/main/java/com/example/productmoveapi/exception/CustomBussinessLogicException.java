package com.example.productmoveapi.exception;

import com.example.productmoveapi.response.ResponseStatusEnum;

/**
 * @author Binh Nguyen Thai at 22:38 on 28/11/2022
 */

public class CustomBussinessLogicException extends ApplicationException {

  private static final long serialVersionUID = -1605187590106478545L;

  public CustomBussinessLogicException(ResponseStatusEnum responseStatusEnum) {
    super(responseStatusEnum);
  }
}
