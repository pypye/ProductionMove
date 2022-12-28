package com.example.productmoveapi.service.agency.implement;

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
import com.example.productmoveapi.service.agency.AgencyRecallProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:36 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyRecallProductServiceImplement implements AgencyRecallProductService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyRecallProductServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> recallProduct(String categoryId) {
    List<Product> productList = productRepository.findAllByCategoryIdAndLocation(categoryId, currentUser()).stream()
        .filter(p -> p.getStatus() == status("2") || p.getStatus() == status("3") || p.getStatus() == status("7"))
        .collect(Collectors.toList());

    productList = productList.stream().peek(p -> p.setStatus(status("10"))).collect(Collectors.toList());
    productRepository.saveAll(productList);
    List<Operation> operationList =
        productList.stream().map(p -> new Operation(p, status("10"), currentUser(), null)).collect(Collectors.toList());
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("recall sucessfully");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> recallProductToWarranty(String warrantyId) {
    List<Product> productList = productRepository.findAllByLocationAndStatus(currentUser(), status("10"));
    ApplicationUser warranty = applicationUserRepository.findById(warrantyId).orElse(null);
    if (warranty == null || !warranty.getRole().getRole().equals("warranty")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    productList = productList.stream().peek(p -> {
      p.setStatus(status("4"));
      p.setNumberOfWarranty(p.getNumberOfWarranty() + 1);
    }).collect(Collectors.toList());
    productRepository.saveAll(productList);
    List<Operation> operationList =
        productList.stream().map(p -> new Operation(p, status("4"), currentUser(), warranty))
            .collect(Collectors.toList());
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("add successfully");
  }
}
