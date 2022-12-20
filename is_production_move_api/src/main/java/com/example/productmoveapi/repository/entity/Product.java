package com.example.productmoveapi.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 17:33 on 20/12/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity{

  private static final long serialVersionUID = -3491868027563406765L;
  @Column(name = "product_code", nullable = false, unique = true)
  private String productCode;

  @Column(name = "product_name", nullable = false, unique = true)
  private String productName;

  @Column(name = "category", nullable = false, unique = true)
  private String category;

  @Column(name = "price", nullable = false, unique = true)
  private String price;

  @Column(name = "description", nullable = false, unique = true)
  private String description;
}
