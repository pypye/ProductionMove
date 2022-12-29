package com.example.productmoveapi.service.agency.management.implement;

import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.agency.management.AgencyReceiveWarrantyProductFromCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:25 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyReceiveWarrantyProductFromCustomerServiceImplement implements
    AgencyReceiveWarrantyProductFromCustomerService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyReceiveWarrantyProductFromCustomerServiceImplement(
      ProductRepository productRepository, ApplicationUserRepository applicationUserRepository,
      StatusRepository statusRepository, OperationRepository operationRepository) {
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.statusRepository = statusRepository;
    this.operationRepository = operationRepository;
  }

  private ApplicationUser currentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String username = userDetails.getUsername();
    return applicationUserRepository.findByUsername(username);
  }

  private Status status(String num) {
    return statusRepository.findById(num).orElse(null);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductCustomer(String productCode) {
    Product product = productRepository.findByProductCode(productCode);
    if (product == null || product.getCustomer() == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    return ResponseFactory.success(product);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductToWarranty(String productCode, String warrantyId) {
    Product product = productRepository.findByProductCode(productCode);
    ApplicationUser warranty = applicationUserRepository.findById(warrantyId).orElse(null);
    if (product == null || product.getCustomer() == null || (product.getStatus() != status("3")
                                                             && product.getStatus() != status("7")) || warranty == null
        || !warranty.getRole().getRole().equals("warranty")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    product.setNumberOfWarranty(product.getNumberOfWarranty() + 1);
    product.setStatus(status("4"));
    productRepository.save(product);
    operationRepository.save(new Operation(product, status("4"), currentUser(), warranty));
    return ResponseFactory.success("add successfully");
  }
}
