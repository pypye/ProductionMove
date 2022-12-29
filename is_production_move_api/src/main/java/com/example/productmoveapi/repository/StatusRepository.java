package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 18:55 on 22/12/2022
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

}
