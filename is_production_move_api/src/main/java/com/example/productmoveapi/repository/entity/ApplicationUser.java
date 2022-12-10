package com.example.productmoveapi.repository.entity;

import com.example.productmoveapi.dto.request.CreateAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id")
  private Role role;

  public void setCreateAccountRequest(CreateAccountRequest createAccountRequest, Role role) {
    this.username = createAccountRequest.getUsername();
    this.password = createAccountRequest.getPassword();
    this.email = createAccountRequest.getEmail();
    this.role = role;
  }
}