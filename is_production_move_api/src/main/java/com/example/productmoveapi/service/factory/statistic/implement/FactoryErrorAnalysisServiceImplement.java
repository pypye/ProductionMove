package com.example.productmoveapi.service.factory.statistic.implement;

import com.example.productmoveapi.dto.request.statistic_request.ErrorAnalysisRequest;
import com.example.productmoveapi.dto.response.product_response.ErrorAnalysisResponse;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.factory.statistic.FactoryErrorAnalysisService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 03:03 on 28/12/2022
 */
@Service
@Slf4j
public class FactoryErrorAnalysisServiceImplement implements FactoryErrorAnalysisService {

  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactoryErrorAnalysisServiceImplement(
      ApplicationUserRepository applicationUserRepository,
      OperationRepository operationRepository, StatusRepository statusRepository) {
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
    return statusRepository.findById("2").orElse(null);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> errorAnalysis(ErrorAnalysisRequest errorAnalysisRequest) {
    List<String> productId =
        operationRepository.findAllByStatusIdAndApplicationUser("1", currentUser()).stream()
            .map(op -> op.getProduct().getId()).collect(
                Collectors.toList());
    List<Product> productList;
    if (errorAnalysisRequest.getAgency().equals("0")) {
      productList = operationRepository.findALlByProductIdInAndStatusAndDestination(productId,
          status(), currentUser()).stream().map(Operation::getProduct).collect(Collectors.toList());
    } else {
      ApplicationUser agency = applicationUserRepository.findById(errorAnalysisRequest.getAgency()).orElse(null);
      productList = operationRepository.findALlByProductIdInAndStatusAndApplicationUser(productId,
          status(), agency).stream().map(Operation::getProduct).collect(Collectors.toList());
    }

    if (!errorAnalysisRequest.getCategory().equals("0")) {
      productList = productList.stream().filter(p -> p.getCategory().getId().equals(errorAnalysisRequest.getCategory()))
          .collect(Collectors.toList());
    }

    int numberOfErrorProduct = (int) productList.stream().filter(p -> p.getNumberOfWarranty() > 0).count();
    return ResponseFactory.success(
        new ErrorAnalysisResponse(numberOfErrorProduct, productList.size() - numberOfErrorProduct));
  }
}
