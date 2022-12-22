package com.example.productmoveapi.service.general.implement;

import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.entity.Category;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.general.ProductInternalService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 12:09 on 21/12/2022
 */
@Service
@Slf4j
public class ProductInternalServiceImplement implements ProductInternalService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Autowired
  public ProductInternalServiceImplement(
      CategoryRepository categoryRepository, ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllCategory() {
    List<Category> categories = categoryRepository.findAll();
    return ResponseFactory.success(categories);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getAllProduct() {
    List<Product> products = productRepository.findAll();
    return ResponseFactory.success(products);
  }
}
