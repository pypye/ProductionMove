package com.example.productmoveapi.repository.entity;

import java.io.Serial;
import java.io.Serializable;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Binh Nguyen Thai at 10:10 on 05/12/2022
 */

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 7559661769624202645L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_time", updatable = false)
  private Date createdTime;

  @Basic
  @UpdateTimestamp
  @Column(name = "last_updated_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdatedTime;

}
