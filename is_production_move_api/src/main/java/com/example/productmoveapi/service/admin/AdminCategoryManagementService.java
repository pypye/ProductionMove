package com.example.productmoveapi.service.admin;

import com.example.productmoveapi.dto.request.category_request.CategoryRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 16:09 on 21/12/2022
 */
public interface AdminCategoryManagementService {

  ResponseEntity<GeneralResponse<Object>> addCategory(CategoryRequest categoryRequest);

  ResponseEntity<GeneralResponse<Object>> deleteCategory(String categoryId);

  ResponseEntity<GeneralResponse<Object>> updateCategory(String categoryId, CategoryRequest categoryRequest);
}
