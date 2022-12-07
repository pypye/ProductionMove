package com.example.productmoveapi.security.filter.service;

import com.example.productmoveapi.repository.ApplicationUserRepository;
import com.example.productmoveapi.repository.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Binh Nguyen Thai at 10:20 on 05/12/2022
 */

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

  private final ApplicationUserRepository applicationUserRepository;

  @Autowired
  public UserDetailsServiceImplement(ApplicationUserRepository applicationUserRepository){
    this.applicationUserRepository = applicationUserRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser =  applicationUserRepository.findByUsername(username);
    if (applicationUser == null){
      throw new UsernameNotFoundException("User not found with " + username);
    }
    return UserDetailsImplement.build(applicationUser);
  }
}
