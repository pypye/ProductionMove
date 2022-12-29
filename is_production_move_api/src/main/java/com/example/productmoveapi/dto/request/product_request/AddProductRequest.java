package com.example.productmoveapi.dto.request.product_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 16:40 on 22/12/2022
 */
@Data
public class AddProductRequest {

  @NotBlank
  @Pattern(message = "Invalid product name", regexp = "^[0-9a-zA-Z /ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀẾỂưạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$")
  private String productName;

  @NotBlank
  @Pattern(message = "Invalid category id", regexp = "^[0-9]*$")
  private String category_id;

  @NotBlank
  @Pattern(message = "Invalid price", regexp = "^[0-9.]*$")
  private String price;

  @NotBlank
  @Pattern(message = "Invalid warrant time", regexp = "^[0-9.]*$")
  private String warrantTime;

  @NotBlank
  private String description;

  @NotBlank
  @Pattern(message = "Invalid number of batch", regexp = "^[0-9]*$")
  private String numberOfBatch;

}
