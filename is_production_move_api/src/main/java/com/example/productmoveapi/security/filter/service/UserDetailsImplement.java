package com.example.productmoveapi.security.filter.service;

import com.example.productmoveapi.repository.entity.ApplicationUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Binh Nguyen Thai at 10:19 on 05/12/2022
 */
public class UserDetailsImplement implements UserDetails {
  private static final long serialVersionUID = 4030000316478277980L;
  private final String id;
  private final String username;
  private final String email;

  @JsonIgnore
  private final String password;
  private final Collection<? extends  GrantedAuthority> authorities;

  public UserDetailsImplement(String id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImplement build(ApplicationUser user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
    return new UserDetailsImplement(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
