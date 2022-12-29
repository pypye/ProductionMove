package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Product;
import com.example.productmoveapi.repository.entity.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 17:40 on 20/12/2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  Product findByProductCode(String productCode);

  Product findByProductCodeAndLocation(String productCode, ApplicationUser location);

  List<Product> findAllByLocationAndStatus(ApplicationUser id, Status status);

  List<Product> findAllByLocationAndIdIn(ApplicationUser location, List<String> id);

  List<Product> findAllByLocation(ApplicationUser location);

  List<Product> findAllByCategoryId(String id);

  List<Product> findAllByCategoryIdAndLocation(String id, ApplicationUser location);

  List<Product> findAllByLocationAndIdInAndStatus(ApplicationUser location, List<String> id, Status status);

  Product findTopByProductNameAndLocationAndStatus(String name, ApplicationUser location, Status status);
}
