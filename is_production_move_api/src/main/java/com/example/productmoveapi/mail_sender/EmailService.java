package com.example.productmoveapi.mail_sender;

/**
 * @author Binh Nguyen Thai at 11:06 on 05/12/2022
 */
public interface EmailService {

  /*
  * @description: configure mailing service for system users
  */
  void sendEmail(String from, String to, String subject, String body);
}
