package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 22:29 on 23/12/2022
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
