package com.example.productmoveapi.service.factory.management.implement;

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
import com.example.productmoveapi.service.factory.management.FactoryImportNewProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 23:51 on 27/12/2022
 */
@Service
@Slf4j
public class FactoryImportNewProductServiceImplement implements FactoryImportNewProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactoryImportNewProductServiceImplement(
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

  private Status status() {
    return statusRepository.findById("1").orElse(null);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProduct(AddProductRequest addProductRequest) {
    Category category = categoryRepository.findById(addProductRequest.getCategory_id()).orElse(null);
    if (category == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }

    List<Product> productList = new ArrayList<>();
    List<Operation> operationList = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(addProductRequest.getNumberOfBatch()); i++){
      String uuid;
      do {
        uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        uuid = "PM" + uuid;
      } while (productRepository.findByProductCode(uuid) != null);
      Product product = new Product(uuid, addProductRequest.getProductName(), category, addProductRequest.getPrice(),
          addProductRequest.getDescription(), status(), currentUser(), null, null, 0,
          Long.parseLong(addProductRequest.getWarrantTime()), null);
//      product.setProductCode(uuid);
//      product.setProductName(addProductRequest.getProductName());
//      product.setCategory(category);
//      product.setPrice(addProductRequest.getPrice());
//      product.setDescription(addProductRequest.getDescription());
//      product.setStatus(status());
//      product.setLocation(currentUser());
//      product.setWarrantTime(Long.parseLong(addProductRequest.getWarrantTime()));
      Operation operation = new Operation(product, status(), currentUser(), null);
      productList.add(product);
      operationList.add(operation);
    }
    productRepository.saveAll(productList);
    operationRepository.saveAll(operationList);
    return ResponseFactory.success("add successfully");
  }
}
