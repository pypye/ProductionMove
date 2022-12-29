package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

/**
 * @author Binh Nguyen Thai at 11:40 on 21/12/2022
 */
@Entity
@Getter
@Table(name = "status")
public class Status extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 4032128372631913871L;

  @Column(name = "name", nullable = false, unique = true)
  private String status;

  @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Operation> operations;

  @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Product> products;
}
