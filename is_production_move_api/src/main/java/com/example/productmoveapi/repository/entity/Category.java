package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 11:40 on 21/12/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {

  private static final long serialVersionUID = -5442339906711889775L;
  @Column(name = "name", nullable = false, unique = true)
  private String category;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Product> products;
}
