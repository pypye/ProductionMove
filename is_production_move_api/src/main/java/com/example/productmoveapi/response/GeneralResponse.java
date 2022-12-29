package com.example.productmoveapi.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 22:31 on 28/11/2022
 */

@Getter
@Setter
public class GeneralResponse<T> {

  private static final long serialVersionUID = 1L;
  private GeneralResponseStatus status;
  private T data;
}

