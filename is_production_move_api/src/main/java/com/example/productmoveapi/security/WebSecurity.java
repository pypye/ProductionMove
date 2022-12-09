package com.example.productmoveapi.security;

import static com.example.productmoveapi.security.SecurityConstants.FORGOT_PASSWORD_URL;
import static com.example.productmoveapi.security.SecurityConstants.LOGIN_URL;
import static com.example.productmoveapi.security.SecurityConstants.LOGOUT_URL;
import static com.example.productmoveapi.security.SecurityConstants.RESET_PASSWORD_URL;
import static com.example.productmoveapi.security.SecurityConstants.SIGN_UP_URL;
import static com.example.productmoveapi.security.SecurityConstants.VERIFY_URL;

import com.example.productmoveapi.security.filter.JWT.AuthEntryPointJWT;
import com.example.productmoveapi.security.filter.authorization.JWTAuthorizationFilter;
import com.example.productmoveapi.security.filter.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Binh Nguyen Thai at 10:16 on 05/12/2022
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImplement userDetailsServiceImplement;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public JWTAuthorizationFilter jwtAuthorizationFilter(){
    return new JWTAuthorizationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public AuthEntryPointJWT unauthorizedHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/auth/login").permitAll()
        .antMatchers("/auth/binh").hasAnyAuthority("factory")
//        .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//        .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
//        .antMatchers(HttpMethod.POST, FORGOT_PASSWORD_URL).permitAll()
//        .antMatchers(HttpMethod.POST, RESET_PASSWORD_URL).permitAll()
//        .antMatchers(HttpMethod.POST, VERIFY_URL).permitAll()
//        .antMatchers(HttpMethod.POST, LOGOUT_URL).permitAll()
        .anyRequest().authenticated();
    http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public  void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
    authenticationManagerBuilder.userDetailsService(userDetailsServiceImplement).passwordEncoder(passwordEncoder());
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource(){
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**",config);
    return source;
  }
}