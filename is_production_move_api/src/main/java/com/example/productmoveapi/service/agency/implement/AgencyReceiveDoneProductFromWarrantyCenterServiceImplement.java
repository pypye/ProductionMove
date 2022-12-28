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
import com.example.productmoveapi.service.agency.AgencyReceiveDoneProductFromWarrantyCenterService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:29 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyReceiveDoneProductFromWarrantyCenterServiceImplement implements
    AgencyReceiveDoneProductFromWarrantyCenterService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyReceiveDoneProductFromWarrantyCenterServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> getProductFromWarranty() {
    return ResponseFactory.success(productRepository.findAllByLocationAndStatus(currentUser(), status("6")));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> returnProductToCustomer(AddProductListRequest addProductListRequest) {
    List<Product> productList = productRepository.findAllByLocationAndIdInAndStatus(currentUser(),
            addProductListRequest.getProduct_id(), status("6")).stream().peek(p -> p.setStatus(status("7")))
        .collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(productList.stream().map(p -> new Operation(p, status("7"), p.getLocation(),
        null)).collect(Collectors.toList()));
    return ResponseFactory.success("return successfully");
  }
}
