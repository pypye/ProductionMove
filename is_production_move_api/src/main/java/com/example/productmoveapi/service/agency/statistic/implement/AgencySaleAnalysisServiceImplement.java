package com.example.productmoveapi.service.agency.statistic.implement;

import com.example.productmoveapi.dto.request.statistic_request.SaleAnalysisRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.service.agency.statistic.AgencySaleAnalysisService;
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
 * @author Binh Nguyen Thai at 01:33 on 28/12/2022
 */
@Service
@Slf4j
public class AgencySaleAnalysisServiceImplement implements AgencySaleAnalysisService {

  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public AgencySaleAnalysisServiceImplement(ApplicationUserRepository applicationUserRepository,
      OperationRepository operationRepository) {
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
  public ResponseEntity<GeneralResponse<Object>> saleAnalysis(SaleAnalysisRequest saleAnalysisRequest) {
    Calendar calendar = Calendar.getInstance();
    List<Operation> operationList =
        operationRepository.findAllByStatusId("3").stream()
            .filter(operation -> operation.getApplicationUser() == currentUser()).collect(Collectors.toList());
    Map<String, Long> counters = operationList.stream().collect(Collectors.groupingBy(p -> {
          calendar.setTime(p.getCreatedTime());
          String option = saleAnalysisRequest.getOption();
          if (option.equals("0")) {
            return calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
          } else if (option.equals("1")) {
            return ((calendar.get(Calendar.MONTH) / 3) + 1) + "-" + calendar.get(Calendar.YEAR);
          } else {
            return Integer.toString(calendar.get(Calendar.YEAR));
          }
        }, LinkedHashMap::new,
        Collectors.counting()));
    return ResponseFactory.success(counters);
  }
}
