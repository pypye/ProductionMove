package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Binh Nguyen Thai at 11:41 on 21/12/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation")
public class Operation extends BaseEntity {

  private static final long serialVersionUID = -592325600705567277L;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private ApplicationUser applicationUser;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "destination_id")
  private ApplicationUser destination;

}
