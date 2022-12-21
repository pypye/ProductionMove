package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author Binh Nguyen Thai at 17:33 on 20/12/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity {

  private static final long serialVersionUID = -3491868027563406765L;
  @Column(name = "product_code", nullable = false, unique = true)
  private String productCode;

  @Column(name = "product_name", nullable = false, unique = true)
  private String productName;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "price", nullable = false, unique = true)
  private String price;

  @Column(name = "description", nullable = false, unique = true)
  private String description;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  @JsonIgnore
  private Collection<Operation> operations;
}
