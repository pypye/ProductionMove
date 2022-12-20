package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:27 on 05/12/2022
 */
@Data
public class UpdateAccountRequest {

  @NotBlank
  @Pattern(message = "Invalid username", regexp = "^(?=[a-zA-Z0-9._]{7,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")
  private String username;

  @NotBlank
  @Pattern(message = "Invalid password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{7,20}$")
  private String password;

  @NotBlank
  @Pattern(message = "Invalid email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;

  @NotBlank
  @Pattern(message = "Invalid role id", regexp = "^[0-9]*$")
  private String role_id;

  @NotBlank
  @Pattern(message = "Invalid company name", regexp = "^[0-9a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$")
  private String companyName;

  @NotBlank
  @Pattern(message = "Invalid address", regexp = "^[0-9a-zA-Z ,/ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$")
  private String address;

  @NotBlank
  @Pattern(message = "Invalid phone", regexp = "(84|0[1-9])+([0-9]{8})\\b")
  private String phone;
}
