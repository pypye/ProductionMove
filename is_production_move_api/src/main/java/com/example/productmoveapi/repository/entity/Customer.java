package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 18:33 on 22/12/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity {

  private static final long serialVersionUID = -1276290940652999391L;
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
  @JsonIgnore
  private Product product;
}
