package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF
            .csrf().disable()

            // Disable default login page
            .httpBasic().disable()
            .formLogin().disable()

            // Stateless
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            // Authorization
            .authorizeRequests()

            // ✅ Swagger paths (ALL required)
            .antMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
            ).permitAll()

            // ✅ Allow all APIs for now
            .antMatchers("/**").permitAll()

            // Anything else
            .anyRequest().authenticated();

        return http.build();
    }
}
