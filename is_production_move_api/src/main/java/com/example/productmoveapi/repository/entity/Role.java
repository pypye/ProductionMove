package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

/**
 * @author Binh Nguyen Thai at 18:19 on 06/12/2022
 */
@Entity
@Getter
@Table(name = "role")
public class Role extends BaseEntity {

  private static final long serialVersionUID = 6015628554509474412L;
  @Column(name = "name")
  private String role;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<ApplicationUser> applicationUser;
}