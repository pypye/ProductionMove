package com.example.productmoveapi.service.factory.statistic.implement;

import com.example.productmoveapi.dto.request.statistic_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.OperationRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import com.example.productmoveapi.service.factory.statistic.FactoryProductReportService;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 11:24 on 29/12/2022
 */

@Service
@Slf4j
public class FactoryProductReportServiceImplement implements FactoryProductReportService {

  private final ApplicationUserRepository applicationUserRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public FactoryProductReportServiceImplement(ApplicationUserRepository applicationUserRepository,
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
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest) {
    String status = staticByStatusYearQuarterMonthRequest.getStatus();
    Calendar calendar = Calendar.getInstance();
    List<Operation> operationList;
    if (status.equals("2")) {
      operationList = operationRepository.findAllByStatusId(status).stream()
          .filter(operation -> operation.getDestination() == currentUser()).collect(Collectors.toList());
    } else if (status.equals("1") || status.equals("9") || status.equals("12")) {
      operationList = operationRepository.findAllByStatusId(status).stream()
          .filter(operation -> operation.getApplicationUser() == currentUser()).collect(Collectors.toList());
    } else {
      return ResponseFactory.error(HttpStatus.valueOf(400), ResponseStatusEnum.WRONG_INFORMATION);
    }
    Map<String, Long> counters = operationList.stream().collect(Collectors.groupingBy(p -> {
          calendar.setTime(p.getCreatedTime());
          String option = staticByStatusYearQuarterMonthRequest.getOption();
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
