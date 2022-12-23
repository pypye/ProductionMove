package com.example.productmoveapi.service.agency.implement;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
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
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 19:24 on 22/12/2022
 */
@Service
@Slf4j
public class AgencyProductManagementServiceImplement implements AgencyProductManagementService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyProductManagementServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> getProductFactory(String factoryId) {
    ApplicationUser factory = applicationUserRepository.findById(factoryId).orElse(null);
    return ResponseFactory.success(productRepository.findAllByLocationAndStatus(factory, status("1")));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductFromFactory(String factoryId,
      AddProductListRequest addProductListRequest) {
    ApplicationUser fatory = applicationUserRepository.findById(factoryId).orElse(null);
    List<Product> productList =
        productRepository.findAllByLocationAndIdIn(fatory, addProductListRequest.getProduct_id());
    productRepository.saveAll(productList.stream().peek(p -> p.setStatus(status("13"))).collect(Collectors.toList()));
    List<Operation> operationList = productList.stream().map(p -> new Operation(p, status("13"), currentUser(),
        fatory)).collect(Collectors.toList());
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("add successfully!");
  }
}
