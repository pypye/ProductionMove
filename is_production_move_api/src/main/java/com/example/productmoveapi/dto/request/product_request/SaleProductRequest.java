package com.example.productmoveapi.dto.request.product_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 22:14 on 23/12/2022
 */
@Data
public class SaleProductRequest {

  @NotBlank
  @Pattern(message = "Invalid code", regexp = "^[a-zA-Z0-9]*$")
  private String productCode;

  @NotBlank
  @Pattern(message = "Invalid company name", regexp = "^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$")
  private String name;

  @NotBlank
  @Pattern(message = "Invalid address", regexp = "^[0-9a-zA-Z ,/ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$")
  private String address;

  @NotBlank
  @Pattern(message = "Invalid phone", regexp = "(84|0[1-9])+([0-9]{8})\\b")
  private String phone;
}
