package com.example.productmoveapi.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author Binh Nguyen Thai at 13:46 on 14/12/2022
 */
@Data
@AllArgsConstructor

public class UserInfoResponse {

  private String id;
  private String username;
  private String email;
  private String type;
  private String companyName;
  private String address;
  private String phone;
}
