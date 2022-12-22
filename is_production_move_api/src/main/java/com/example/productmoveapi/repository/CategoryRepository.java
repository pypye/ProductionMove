package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 12:15 on 21/12/2022
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Category findByCategory(String category);
}
