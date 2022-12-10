package com.example.productmoveapi.repository;

import com.example.productmoveapi.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Binh Nguyen Thai at 23:55 on 07/12/2022
 */
public interface RoleRepository extends JpaRepository<Role, String> {

}
