package com.example.productmoveapi.service.warranty.implement;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.warranty.WarrantyProductManagementService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 15:32 on 24/12/2022
 */
@Service
@Slf4j
public class WarrantyProductManagementServiceImplement implements WarrantyProductManagementService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public WarrantyProductManagementServiceImplement(
      ProductRepository productRepository, CategoryRepository categoryRepository,
      ApplicationUserRepository applicationUserRepository,
      OperationRepository operationRepository, StatusRepository statusRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
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
  public ResponseEntity<GeneralResponse<Object>> getProductFromAgency() {
    return ResponseFactory.success(
        operationRepository.findAllByStatusAndDestination(status("4"), currentUser()));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductFromAgency(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndDestination(
            addProductListRequest.getProduct_id(), status("4"), currentUser()).stream()
        .filter(opt -> opt.getProduct().getStatus() == status("4")).collect(Collectors.toList());
    Map<String, Operation> operationMap =
        operationList.stream().collect(Collectors.toMap(operation -> operation.getProduct().getId(), Function.identity()
            , (opt1, opt2) -> opt2));
    operationList = new ArrayList<>(operationMap.values());
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("5"));
      p.getProduct().setLocation(p.getDestination());
    }).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(
        operationList.stream().map(p -> new Operation(p.getProduct(), status("5"), p.getDestination(),
            p.getApplicationUser())).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductInWarranty() {
    return ResponseFactory.success(
        productRepository.findAllByLocationAndStatus(currentUser(), status("5")));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndAndApplicationUser(
            addProductListRequest.getProduct_id(), status("5"), currentUser()).stream()
        .filter(opt -> opt.getProduct().getStatus() == status("5")).collect(Collectors.toList());
    Map<String, Operation> operationMap =
        operationList.stream().collect(Collectors.toMap(operation -> operation.getProduct().getId(), Function.identity()
            , (opt1, opt2) -> opt2));
    operationList = new ArrayList<>(operationMap.values());
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("6"));
      p.getProduct().setLocation(p.getDestination());
    }).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(productList.stream().map(p -> new Operation(p, status("6"), p.getLocation(),
        null)).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }
}
