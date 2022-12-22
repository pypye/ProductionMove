package com.example.productmoveapi.service.factory.implement;

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
import java.util.UUID;
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
    Product product = new Product();
    String uuid = "";
    do {
      uuid = UUID.randomUUID().toString().replace("-", "");
    } while (productRepository.findByProductCode(uuid) != null);
    product.setProductCode(uuid);
    product.setProductName(addProductRequest.getProductName());
    product.setCategory(category);
    product.setPrice(addProductRequest.getPrice());
    product.setDescription(addProductRequest.getDescription());
    product.setStatus("1");
    productRepository.save(product);
    Operation operation = new Operation(product, status("1"), currentUser());
    operationRepository.save(operation);
    return ResponseFactory.success("add successfully");
  }
}
