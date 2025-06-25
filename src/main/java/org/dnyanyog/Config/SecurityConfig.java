package org.dnyanyog.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults; // Import this!

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authz -> 
                    authz
                        .requestMatchers("auth/validate").permitAll()
                        .requestMatchers("/api/products").permitAll()
                        .requestMatchers("/api/products/search/{productId}/{productName}").permitAll()
                        .requestMatchers("/api/products/update/{productId}").permitAll()
                        
                        .requestMatchers("/api/products/{productId}").permitAll()
                        .requestMatchers("/users/add").permitAll()
                        .requestMatchers("/users/search").permitAll()
                        .requestMatchers("/users/update/{userId}").permitAll()
                        .requestMatchers("/users/delete/{userId}").permitAll()
                        
                        
                        
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()) // No more error
                .csrf(csrf -> csrf.disable())
                .build();
    
}}
