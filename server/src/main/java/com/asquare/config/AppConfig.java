package com.asquare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

  @Bean // Compulsory to Annotate with Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csf -> csf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll()// Allow POST /public to be publicly accessible for creating user
            .requestMatchers("/private/**").authenticated()// Block all the /private and require authentication
            .anyRequest().permitAll())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
