package com.example.productmoveapi.service.factory.implement;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.dto.request.product_request.AddProductRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Category;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.factory.FactoryProductManagementService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 16:36 on 22/12/2022
 */
@Service
@Slf4j
public class FactoryProductManagementServiceImplement implements FactoryProductManagementService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactoryProductManagementServiceImplement(
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
  public ResponseEntity<GeneralResponse<Object>> addProduct(AddProductRequest addProductRequest) {
    Category category = categoryRepository.findById(addProductRequest.getCategory_id()).orElse(null);
    if (category == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    String uuid;
    do {
      uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
      uuid = "PM" + uuid;
    } while (productRepository.findByProductCode(uuid) != null);
    Product product = new Product();
    product.setProductCode(uuid);
    product.setProductName(addProductRequest.getProductName());
    product.setCategory(category);
    product.setPrice(addProductRequest.getPrice());
    product.setDescription(addProductRequest.getDescription());
    product.setStatus(status("1"));
    product.setLocation(currentUser());
    product.setWarrantTime(Long.parseLong(addProductRequest.getWarrantTime()));
    productRepository.save(product);
    Operation operation = new Operation(product, status("1"), currentUser(), null);
    operationRepository.save(operation);
    return ResponseFactory.success("add successfully");
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
