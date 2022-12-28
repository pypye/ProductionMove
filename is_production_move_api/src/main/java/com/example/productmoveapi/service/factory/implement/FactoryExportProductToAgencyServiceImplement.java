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
import com.example.productmoveapi.service.factory.FactoryExportProductToAgencyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 23:56 on 27/12/2022
 */
@Service
@Slf4j
public class FactoryExportProductToAgencyServiceImplement implements FactoryExportProductToAgencyService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactoryExportProductToAgencyServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> getProductFromAgency() {
    return ResponseFactory.success(
        operationRepository.findAllByStatusAndDestination(status("13"), currentUser()).stream()
            .map(Operation::getProduct)
            .filter(product -> product.getStatus() == status("13")).collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductToAgency(AddProductListRequest addProductListRequest) {
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndDestination(
            addProductListRequest.getProduct_id(), status("13"), currentUser())
        .stream().filter(opt -> opt.getProduct().getStatus() == status("13")).collect(Collectors.toList());
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("2"));
      p.getProduct().setLocation(p.getApplicationUser());
    }).map(
        Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(productList.stream().map(p -> new Operation(p, status("2"), p.getLocation(),
        null)).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }
}
