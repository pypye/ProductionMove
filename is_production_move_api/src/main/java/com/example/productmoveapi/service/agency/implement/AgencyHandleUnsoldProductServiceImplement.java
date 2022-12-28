package com.example.productmoveapi.service.agency.implement;

import com.example.productmoveapi.dto.request.product_request.AddProductListRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.ProductRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.agency.AgencyHandleUnsoldProductService;
import java.time.Year;
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
 * @author Binh Nguyen Thai at 00:40 on 28/12/2022
 */
@Service
@Slf4j
public class AgencyHandleUnsoldProductServiceImplement implements AgencyHandleUnsoldProductService {

  private final ProductRepository productRepository;
  private final ApplicationUserRepository applicationUserRepository;
  private final StatusRepository statusRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencyHandleUnsoldProductServiceImplement(
      ProductRepository productRepository, ApplicationUserRepository applicationUserRepository,
      StatusRepository statusRepository, OperationRepository operationRepository) {
    this.productRepository = productRepository;
    this.applicationUserRepository = applicationUserRepository;
    this.statusRepository = statusRepository;
    this.operationRepository = operationRepository;
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
  public ResponseEntity<GeneralResponse<Object>> getProductUnsold() {
    int now = Year.now().getValue();
    Calendar calendar = Calendar.getInstance();
    List<Product> productList = productRepository.findAllByLocationAndStatus(currentUser(), status("2")).stream()
        .filter(product -> {
          calendar.setTime(product.getLastUpdatedTime());
          return now != calendar.get(Calendar.YEAR);
        }).collect(Collectors.toList());
    return ResponseFactory.success(productList);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> addProductUnsoldToFactory(
      AddProductListRequest addProductListRequest) {
    int now = Year.now().getValue();
    Calendar calendar = Calendar.getInstance();
    List<Operation> operationList =
        operationRepository.findALlByProductIdInAndStatusAndApplicationUser(addProductListRequest.getProduct_id(),
                status("13"), currentUser()).stream().filter(operation -> operation.getProduct().getStatus() == status("2"))
            .filter(operation -> {
              calendar.setTime(operation.getProduct().getLastUpdatedTime());
              return now != calendar.get(Calendar.YEAR);
            }).collect(Collectors.toList());
    List<Product> productList = operationList.stream().peek(p -> {
      p.getProduct().setStatus(status("12"));
      p.getProduct().setLocation(p.getDestination());
    }).map(Operation::getProduct).collect(Collectors.toList());
    productRepository.saveAll(productList);
    operationRepository.saveAll(
        operationList.stream().map(operation -> new Operation(operation.getProduct(), status("12"),
            operation.getDestination(), null)).collect(Collectors.toList()));
    return ResponseFactory.success("add successfully");
  }
}
