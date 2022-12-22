package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.ApplicationUser;
import com.example.productmoveapi.repository.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 10:42 on 05/12/2022
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

  ApplicationUser findByUsername(String username);

  ApplicationUser findByEmail(String email);

  ApplicationUser findByCompanyName(String company_name);

  List<ApplicationUser> findAllByRole(Role role);
}