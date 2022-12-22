package com.example.productmoveapi.service.agency.implement;

import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 19:24 on 22/12/2022
 */
@Service
@Slf4j
public class AgencyProductManagementServiceImplement implements AgencyProductManagementService {

  private final ProductRepository productRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public AgencyProductManagementServiceImplement(
      ProductRepository productRepository, StatusRepository statusRepository) {
    this.productRepository = productRepository;
    this.statusRepository = statusRepository;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductFactory() {
    return ResponseFactory.success(productRepository.findAllByStatus("1"));
  }
}
