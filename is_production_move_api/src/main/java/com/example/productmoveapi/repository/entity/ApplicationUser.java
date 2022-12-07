package com.example.productmoveapi.repository.entity;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 22:46 on 28/11/2022
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class ApplicationUser extends BaseEntity {

  private static final long serialVersionUID = 1823759985171944176L;
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "status", nullable = false)
  private boolean status;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;

  public void setCreateAccountRequest(CreateAccountRequest createAccountRequest) {
    this.username = createAccountRequest.getUsername();
    this.password = createAccountRequest.getPassword();
    this.email = createAccountRequest.getEmail();
    this.status = false;
  }
}

