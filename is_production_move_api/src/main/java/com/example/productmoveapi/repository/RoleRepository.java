package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Binh Nguyen Thai at 23:55 on 07/12/2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  Role findRoleById(String id);
}
