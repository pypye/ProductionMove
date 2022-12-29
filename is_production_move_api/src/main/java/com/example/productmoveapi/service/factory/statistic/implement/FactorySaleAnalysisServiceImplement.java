package com.example.productmoveapi.service.factory.statistic.implement;

import com.example.productmoveapi.dto.request.statistic_request.SaleAnalysisRequest;
import com.example.productmoveapi.dto.response.product_response.SaleAnalysisResponse;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.StatusRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Status;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.factory.statistic.FactorySaleAnalysisService;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 02:30 on 28/12/2022
 */
@Service
@Slf4j
public class FactorySaleAnalysisServiceImplement implements FactorySaleAnalysisService {

  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;
  private final StatusRepository statusRepository;

  @Autowired
  public FactorySaleAnalysisServiceImplement(ApplicationUserRepository applicationUserRepository,
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
    return statusRepository.findById("3").orElse(null);
  }

  @Override
  public ResponseEntity<GeneralResponse<Object>> saleAnalysis(SaleAnalysisRequest saleAnalysisRequest) {
    Calendar calendar = Calendar.getInstance();
    List<String> productIdList =
        operationRepository.findAllByStatusId("1").stream()
            .filter(operation -> operation.getApplicationUser() == currentUser()).map(op -> op.getProduct().getId())
            .collect(Collectors.toList());
    List<Operation> operationList = operationRepository.findALlByProductIdInAndStatusAndDestination(productIdList,
        status(), null);
    Map<String, SaleAnalysisResponse> counters = operationList.stream().collect(Collectors.groupingBy(p -> {
              calendar.setTime(p.getCreatedTime());
              String option = saleAnalysisRequest.getOption();
              if (option.equals("0")) {
                return (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
              } else if (option.equals("1")) {
                return ((calendar.get(Calendar.MONTH) / 3) + 1) + "-" + calendar.get(Calendar.YEAR);
              } else {
                return Integer.toString(calendar.get(Calendar.YEAR));
              }
            }, LinkedHashMap::new,
            Collectors.summarizingLong(opt -> Long.parseLong(opt.getProduct().getPrice().replace(".", ""))))).entrySet()
        .stream().collect(Collectors.toMap(Map.Entry::getKey,
            e -> new SaleAnalysisResponse(e.getValue().getCount(), e.getValue().getSum()), (x, y) -> y,
            LinkedHashMap::new));
    return ResponseFactory.success(counters);
  }
}
