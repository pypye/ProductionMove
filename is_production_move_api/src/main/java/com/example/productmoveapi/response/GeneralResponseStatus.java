package com.example.productmoveapi.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 22:31 on 28/11/2022
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseStatus implements Serializable {

  private static final long serialVersionUID = 1216664062736095390L;

  private String code;

  private String message;

  public GeneralResponseStatus(ResponseStatusEnum responseStatusEnum) {
    this.code = responseStatusEnum.getCode();
    this.message = responseStatusEnum.getMessage();
  }
}