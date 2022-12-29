package com.example.productmoveapi.service.agency.management.implement;

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
import com.example.productmoveapi.service.agency.management.AgencyImportProductFromFactoryService;
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
 * @author Binh Nguyen Thai at 00:16 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyImportProductFromFactoryServiceImplement implements AgencyImportProductFromFactoryService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyImportProductFromFactoryServiceImplement(
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
    ApplicationUser factory = applicationUserRepository.findById(factoryId).orElse(null);
    if (factory == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    List<Product> productList = productRepository.findAllByLocationAndIdIn(factory,
            addProductListRequest.getProduct_id()).stream().filter(opt -> opt.getStatus() == status("1"))
        .collect(Collectors.toList());
    productRepository.saveAll(productList.stream().peek(p -> p.setStatus(status("13"))).collect(Collectors.toList()));
    List<Operation> operationList = productList.stream().map(p -> new Operation(p, status("13"), currentUser(),
        factory)).collect(Collectors.toList());
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("add successfully!");
  }
}
