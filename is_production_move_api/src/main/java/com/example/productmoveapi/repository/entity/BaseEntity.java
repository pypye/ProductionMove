package com.example.productmoveapi.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
  @JsonIgnore
  @Column(name = "created_time", updatable = false)
  private Date createdTime;

  @Basic
  @UpdateTimestamp
  @JsonIgnore
  @Column(name = "last_updated_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdatedTime;

}