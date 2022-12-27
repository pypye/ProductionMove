package com.example.productmoveapi.service.agency.implement;

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
import com.example.productmoveapi.service.agency.AgencySellProductToCustomerService;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:21 on 28/12/2022
 */
@Service
@Slf4j
public class AgencySellProductToCustomerServiceImplement implements AgencySellProductToCustomerService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;
  private final CustomerRepository customerRepository;

  @Autowired
  public AgencySellProductToCustomerServiceImplement(
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
}
