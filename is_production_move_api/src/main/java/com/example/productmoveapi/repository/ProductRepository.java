package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 17:40 on 20/12/2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  Product findByProductCode(String productCode);

  List<Product> findAllByLocationAndStatus(String id, String status);
}
