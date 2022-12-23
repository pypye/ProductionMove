package com.example.productmoveapi.dto.request.product_request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 00:05 on 23/12/2022
 */

@Data
public class AddProductFromFactoryRequest {

  @NotEmpty
  private List<String> product_id;
}
