package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 18:56 on 22/12/2022
 */
@Repository
public interface OperationRepository extends JpaRepository<Operation, String> {

}
