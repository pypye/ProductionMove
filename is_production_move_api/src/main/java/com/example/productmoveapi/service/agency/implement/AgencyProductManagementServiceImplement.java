package com.example.productmoveapi.service.agency.implement;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.SaleProductRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.CustomerRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Customer;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.agency.AgencyProductManagementService;
import java.util.Date;
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
 * @author Binh Nguyen Thai at 19:24 on 22/12/2022
 */
@Service
@Slf4j
public class AgencyProductManagementServiceImplement implements AgencyProductManagementService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;
  private final CustomerRepository customerRepository;

  @Autowired
  public AgencyProductManagementServiceImplement(
      ProductRepository productRepository, ApplicationUserRepository applicationUserRepository,
      StatusRepository statusRepository, OperationRepository operationRepository,
      CustomerRepository customerRepository) {
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.statusRepository = statusRepository;
    this.operationRepository = operationRepository;
    this.customerRepository = customerRepository;
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
    List<Product> productList = productRepository.findAllByLocationAndIdIn(factory,
            addProductListRequest.getProduct_id()).stream().filter(opt -> opt.getStatus() == status("1"))
        .collect(Collectors.toList());
    productRepository.saveAll(productList.stream().peek(p -> p.setStatus(status("13"))).collect(Collectors.toList()));
    List<Operation> operationList = productList.stream().map(p -> new Operation(p, status("13"), currentUser(),
        factory)).collect(Collectors.toList());
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("add successfully!");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductNotCustomer() {
    List<Product> product = productRepository.findAllByLocationAndStatus(currentUser(), status("2"));
    return ResponseFactory.success(product);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> saleForCustomer(SaleProductRequest saleProductRequest) {
    Product product = productRepository.findByProductCodeAndLocation(saleProductRequest.getProductCode(),
        currentUser());
    if (product == null || product.getCustomer() != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    product.setStatus(status("3"));
    product.setSalesTime(new Date());
    Customer customer = new Customer(saleProductRequest.getName(), saleProductRequest.getAddress(),
        saleProductRequest.getPhone(), product);
    customerRepository.save(customer);
    product.setCustomer(customer);
    productRepository.save(product);
    operationRepository.save(new Operation(product, status("3"), currentUser(), null));
    return ResponseFactory.success("sale successfully!");
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
    if (product == null || product.getCustomer() == null || product.getStatus() != status("3")
        || product.getStatus() != status("7") || warranty == null
        || !warranty.getRole().getRole().equals("warranty")) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    product.setNumberOfWarranty(product.getNumberOfWarranty() + 1);
    product.setStatus(status("4"));
    productRepository.save(product);
    operationRepository.save(new Operation(product, status("4"), currentUser(), warranty));
    return ResponseFactory.success("add successfully");
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
