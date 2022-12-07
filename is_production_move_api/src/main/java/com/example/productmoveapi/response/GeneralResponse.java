package com.example.productmoveapi.response;

/**
 * @author Binh Nguyen Thai at 22:31 on 28/11/2022
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse<T> {

  private static final long serialVersionUID = 1L;
  private GeneralResponseStatus status;
  private T data;
}

