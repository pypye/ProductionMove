package com.example.productmoveapi.service.admin.management.implement;

import com.example.productmoveapi.dto.request.category_request.CategoryRequest;
import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.entity.Category;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.admin.management.AdminCategoryManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 16:09 on 21/12/2022
 */
@Service
@Slf4j
public class AdminCategoryManagementServiceImplement implements AdminCategoryManagementService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public AdminCategoryManagementServiceImplement(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addCategory(CategoryRequest categoryRequest) {
    String name = categoryRequest.getName();
    if (categoryRepository.findByCategory(name) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.EXISTED_CATEGORY);
    }
    Category category = new Category();
    category.setCategory(name);
    categoryRepository.save(category);
    return ResponseFactory.success("add successfully");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> deleteCategory(String categoryId) {
    Category category = categoryRepository.findById(categoryId).orElse(null);
    if (category == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    categoryRepository.deleteById(categoryId);
    return ResponseFactory.success("delete succesfully!");
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> updateCategory(String categoryId, CategoryRequest categoryRequest) {
    Category category = categoryRepository.findById(categoryId).orElse(null);
    if (category == null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.WRONG_INFORMATION);
    }
    if (categoryRepository.findByCategory(categoryRequest.getName()) != null) {
      return ResponseFactory.error(HttpStatus.valueOf(403), ResponseStatusEnum.EXISTED_CATEGORY);
    }
    category.setCategory(categoryRequest.getName());
    categoryRepository.save(category);
    return ResponseFactory.success("update succesfully!");
  }
}
