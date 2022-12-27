package com.example.productmoveapi.service.general.implement;

import com.example.productmoveapi.dto.request.static_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.CategoryRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Category;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.general.ProductReportGeneralService;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
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
public class ProductReportGeneralServiceImplement implements ProductReportGeneralService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public ProductReportGeneralServiceImplement(
      CategoryRepository categoryRepository, ProductRepository productRepository,
      ApplicationUserRepository applicationUserRepository, OperationRepository operationRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.operationRepository = operationRepository;
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

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest) {
    List<Operation> operationList = operationRepository.findAllByStatusId(
        staticByStatusYearQuarterMonthRequest.getStatus());
    if (!currentUser().getRole().getRole().equals("admin")) {
      operationList = operationList.stream().filter(operation -> operation.getApplicationUser() == currentUser())
          .collect(Collectors.toList());
    }
    Calendar calendar = Calendar.getInstance();
    int year = Integer.parseInt(staticByStatusYearQuarterMonthRequest.getYear());
    if (year != 0) {
      operationList = operationList.stream().filter(operation -> {
        calendar.setTime(operation.getCreatedTime());
        return year == calendar.get(Calendar.YEAR);
      }).collect(Collectors.toList());
    }

    int quarter = Integer.parseInt(staticByStatusYearQuarterMonthRequest.getQuarter());
    if (quarter != 0) {
      operationList = operationList.stream().filter(operation -> {
        calendar.setTime(operation.getCreatedTime());
        return quarter == (calendar.get(Calendar.MONTH) / 3) + 1;
      }).collect(Collectors.toList());
    }

    int month = Integer.parseInt(staticByStatusYearQuarterMonthRequest.getMonth());
    if (month != 0) {
      operationList = operationList.stream().filter(operation -> {
        calendar.setTime(operation.getCreatedTime());
        return month == calendar.get(Calendar.MONTH) + 1;
      }).collect(Collectors.toList());
    }
    return ResponseFactory.success(operationList);
  }
}
