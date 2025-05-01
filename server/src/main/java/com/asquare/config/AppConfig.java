package com.asquare.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

  @Bean // Compulsory to Annotate with Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll()// Allow POST /public to be publicly accessible for creating user
            .requestMatchers("/private/**").authenticated()// Block all the /private and require authentication
            .anyRequest().permitAll())
        .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csf -> csf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()));
    return http.build();
  }

  private CorsConfigurationSource corsConfigurationSource() {
    return new CorsConfigurationSource() {
      @Override
      @Nullable
      public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList(
            "http://localhost:5173"));
        cfg.setAllowedMethods(Collections.singletonList("*"));
        cfg.setAllowCredentials(true);
        cfg.setAllowedHeaders(Collections.singletonList("*"));
        cfg.setExposedHeaders(Arrays.asList("Authorization"));
        cfg.setMaxAge(3600L);
        return cfg;
      }
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
