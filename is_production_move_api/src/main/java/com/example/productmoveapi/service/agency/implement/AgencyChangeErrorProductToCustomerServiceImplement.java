package com.example.productmoveapi.service.agency.implement;

import com.example.productmoveapi.dto.request.product_request.ChangeProductRequest;
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
import com.example.productmoveapi.service.agency.AgencyChangeErrorProductToCustomerService;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:32 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyChangeErrorProductToCustomerServiceImplement implements AgencyChangeErrorProductToCustomerService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;
  private final CustomerRepository customerRepository;

  @Autowired
  public AgencyChangeErrorProductToCustomerServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> getProductErrorFromWarranty() {
    return ResponseFactory.success(
        operationRepository.findAllByStatusAndDestination(status("5"), currentUser()).stream()
            .map(Operation::getProduct).filter(
                product -> (product.getStatus() == status("8") || product.getStatus() == status("9")) && (
                    product.getCustomer() != null)).collect(Collectors.toSet()));
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> changeProductErrorToCustomer(
      ChangeProductRequest changeProductRequest) {
    Set<Product> products = operationRepository.findAllByStatusAndDestination(status("5"), currentUser()).stream()
        .map(Operation::getProduct).filter(
            product -> (product.getStatus() == status("8") || product.getStatus() == status("9")) && (
                product.getCustomer() != null)).collect(Collectors.toSet());
    Product oldProduct =
        products.stream().filter(p -> p.getProductCode().equals(changeProductRequest.getProductCode())).findFirst()
            .orElse(null);
    if (oldProduct == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    Product newProduct = productRepository.findTopByProductNameAndLocationAndStatus(oldProduct.getProductName(),
        currentUser(), status("2"));
    if (newProduct == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.NOT_MATCHING_PRODUCT_FOUND);
    }
    newProduct.setStatus(status("3"));
    newProduct.setSalesTime(new Date());
    Customer customer = oldProduct.getCustomer();
    customerRepository.save(customer);
    newProduct.setCustomer(customer);
    oldProduct.setCustomer(null);
    productRepository.save(newProduct);
    productRepository.save(oldProduct);
    operationRepository.save(new Operation(newProduct, status("3"), currentUser(), null));
    return ResponseFactory.success(newProduct);
  }
}
