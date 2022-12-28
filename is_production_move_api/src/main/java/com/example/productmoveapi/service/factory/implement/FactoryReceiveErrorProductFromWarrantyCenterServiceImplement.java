package com.example.productmoveapi.service.factory.implement;

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
import com.example.productmoveapi.service.factory.FactoryReceiveErrorProductFromWarrantyCenterService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:00 on 28/12/2022
 */
@Service
@Slf4j
public class FactoryReceiveErrorProductFromWarrantyCenterServiceImplement implements
    FactoryReceiveErrorProductFromWarrantyCenterService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactoryReceiveErrorProductFromWarrantyCenterServiceImplement(
      ProductRepository productRepository, ApplicationUserRepository applicationUserRepository,
      OperationRepository operationRepository, StatusRepository statusRepository) {
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.operationRepository = operationRepository;
    this.statusRepository = statusRepository;
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
    return ResponseFactory.success(
        operationRepository.findAllByStatusAndDestination(status("8"), currentUser()).stream()
            .map(Operation::getProduct).filter(product -> product.getStatus() == status("8"))
            .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductFromWarranty(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndDestination(
            addProductListRequest.getProduct_id(), status("8"), currentUser()).stream()
        .filter(opt -> opt.getProduct().getStatus() == status("8")).collect(Collectors.toList());
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("9"));
      p.getProduct().setLocation(p.getDestination());
    }).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(
        operationList.stream().map(p -> new Operation(p.getProduct(), status("9"), p.getDestination(),
            null)).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }
}
