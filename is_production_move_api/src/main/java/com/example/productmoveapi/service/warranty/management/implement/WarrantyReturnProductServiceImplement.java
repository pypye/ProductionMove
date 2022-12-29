package com.example.productmoveapi.service.warranty.management.implement;

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
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.warranty.management.WarrantyReturnProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:12 on 28/12/2022
 */
@Slf4j
@Service
public class WarrantyReturnProductServiceImplement implements WarrantyReturnProductService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public WarrantyReturnProductServiceImplement(ProductRepository productRepository,
      ApplicationUserRepository applicationUserRepository, OperationRepository operationRepository,
      StatusRepository statusRepository) {
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

  private List<Operation> getOperationListByCurrentUser(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndApplicationUser(
            addProductListRequest.getProduct_id(), status("5"), currentUser()).stream()
        .filter(opt -> opt.getProduct().getStatus() == status("5")).collect(Collectors.toList());
    Map<String, Operation> operationMap =
        operationList.stream().collect(Collectors.toMap(operation -> operation.getProduct().getId(), Function.identity()
            , (opt1, opt2) -> opt2));
    operationList = new ArrayList<>(operationMap.values());
    return operationList;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductInWarranty() {
    return ResponseFactory.success(
        productRepository.findAllByLocationAndStatus(currentUser(), status("5")));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductDoneToAgency(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = getOperationListByCurrentUser(addProductListRequest);
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("6"));
      p.getProduct().setLocation(p.getDestination());
    }).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(productList.stream().map(p -> new Operation(p, status("6"), p.getLocation(),
        currentUser())).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductErrorToFactory(AddProductListRequest addProductListRequest
      , String factoryId) {
    ApplicationUser factory = applicationUserRepository.findById(factoryId).orElse(null);
    if (factory == null || !factory.getRole().getRole().equals("factory")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    List<Operation> operationList = getOperationListByCurrentUser(addProductListRequest);
    List<Product> productList = operationList.stream().peek(p -> p.getProduct().setStatus(status("8"))).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(productList.stream().map(p -> new Operation(p, status("8"), p.getLocation(),
        factory)).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }
}
