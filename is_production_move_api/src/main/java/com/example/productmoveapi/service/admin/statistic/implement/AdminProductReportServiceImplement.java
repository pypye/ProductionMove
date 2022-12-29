package com.example.productmoveapi.service.admin.statistic.implement;

import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.admin.statistic.AdminProductReportService;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 00:42 on 30/12/2022
 */

@Service
@Slf4j
public class AdminProductReportServiceImplement implements AdminProductReportService {

  private final ProductRepository productRepository;

  @Autowired
  public AdminProductReportServiceImplement(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusAndRole(String statusId, String roleId) {
    List<Product> productList = productRepository.findAll().stream().filter(
        product -> product.getStatus().getId().equals(statusId) && product.getLocation().getRole().getId()
            .equals(roleId)).collect(Collectors.toList());
    Map<String, Long> counters = productList.stream()
        .collect(Collectors.groupingBy(p -> p.getLocation().getCompanyName(), TreeMap::new, Collectors.counting()));
    return ResponseFactory.success(counters);
  }
}
