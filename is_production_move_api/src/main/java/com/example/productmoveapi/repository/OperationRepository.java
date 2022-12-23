package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Operation;
import com.example.productmoveapi.repository.entity.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 18:56 on 22/12/2022
 */
@Repository
public interface OperationRepository extends JpaRepository<Operation, String> {

  List<Operation> findAllByStatusAndDestination(Status status, ApplicationUser des);

  List<Operation> findALlByProductIdInAndStatusAndDestination(List<String> id, Status status, ApplicationUser des);
}
