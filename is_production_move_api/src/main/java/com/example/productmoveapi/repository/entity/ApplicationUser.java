package com.example.productmoveapi.repository.entity;

import com.example.productmoveapi.dto.request.user_request.CreateAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @Serial
  private static final long serialVersionUID = 1823759985171944176L;
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @Column(name = "company_name", nullable = false, unique = true)
  private String companyName;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Operation> operations;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Product> products;

  @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Operation> operations_des;

  public void setCreateAccountRequest(CreateAccountRequest createAccountRequest, Role role) {
    this.username = createAccountRequest.getUsername();
    this.password = createAccountRequest.getPassword();
    this.email = createAccountRequest.getEmail();
    this.role = role;
    this.companyName = createAccountRequest.getCompanyName();
    this.address = createAccountRequest.getAddress();
    this.phone = createAccountRequest.getPhone();
  }
}