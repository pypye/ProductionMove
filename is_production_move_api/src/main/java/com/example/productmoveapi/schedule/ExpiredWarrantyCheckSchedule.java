package com.example.productmoveapi.schedule;

import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 10:34 on 27/12/2022
 */

@EnableScheduling
@Slf4j
@Service
public class ExpiredWarrantyCheckSchedule {

  private final ProductRepository productRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public ExpiredWarrantyCheckSchedule(ProductRepository productRepository, StatusRepository statusRepository) {
    this.productRepository = productRepository;
    this.statusRepository = statusRepository;
  }

  private Status status() {
    return statusRepository.findById("11").orElse(null);
  }

  /*
  * @description: At 0:00 every day, the system will check the warranty period of the products
  */

  @Scheduled(cron = "0 0 0 * * *")
  public void checkExpireProduct() {
    Date now = new Date();
    Calendar calendar = Calendar.getInstance();
    List<Product> productList =
        productRepository.findAll().stream()
            .filter(p -> p.getStatus().getId().equals("3") || p.getStatus().getId().equals("7"))
            .filter(p -> {
              calendar.setTime(now);
              calendar.add(Calendar.MONTH, (int) -p.getWarrantTime());
              return p.getSalesTime().compareTo(calendar.getTime()) < 0;
            })
            .peek(product -> product.setStatus(status()))
            .collect(Collectors.toList());
    productRepository.saveAll(productList);
  }
}
