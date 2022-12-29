package com.example.productmoveapi.exception;

import com.example.productmoveapi.response.ResponseStatusEnum;
import java.io.Serial;

/**
 * @author Binh Nguyen Thai at 22:38 on 28/11/2022
 */

public class CustomBussinessLogicException extends ApplicationException {

  @Serial
  private static final long serialVersionUID = -1605187590106478545L;

  public CustomBussinessLogicException(ResponseStatusEnum responseStatusEnum) {
    super(responseStatusEnum);
  }
}
