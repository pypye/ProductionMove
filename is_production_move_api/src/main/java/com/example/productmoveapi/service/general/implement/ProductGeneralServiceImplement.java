package com.example.productmoveapi.service.general.implement;

import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Category;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.general.ProductGeneralService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 12:09 on 21/12/2022
 */
@Service
@Slf4j
public class ProductGeneralServiceImplement implements ProductGeneralService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;

  @Autowired
  public ProductGeneralServiceImplement(
      CategoryRepository categoryRepository, ProductRepository productRepository,
      ApplicationUserRepository applicationUserRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
  }

  private ApplicationUser currentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String username = userDetails.getUsername();
    return applicationUserRepository.findByUsername(username);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllCategory() {
    List<Category> categories = categoryRepository.findAll();
    return ResponseFactory.success(categories);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllProduct() {
    List<Product> products;
    if (currentUser().getRole().getRole().equals("admin")) {
      products = productRepository.findAll();
    } else {
      String username = currentUser().getUsername();
      products = productRepository.findAllByLocation(applicationUserRepository.findByUsername(username));
    }
    return ResponseFactory.success(products);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllProductByCategory(String categoryId) {
    List<Product> products;
    if (currentUser().getRole().getRole().equals("admin")) {
      products = productRepository.findAllByCategoryId(categoryId);
    } else {
      String username = currentUser().getUsername();
      products = productRepository.findAllByCategoryIdAndLocation(categoryId,
          applicationUserRepository.findByUsername(username));
    }
    return ResponseFactory.success(products);
  }
}
