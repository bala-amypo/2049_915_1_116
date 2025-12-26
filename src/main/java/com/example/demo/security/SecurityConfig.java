package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtUtil;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔐 JWT util bean
    @Bean
    public JwtUtil jwtUtil() {
        // secret must be at least 32 chars
        return new JwtUtil(
                "mysecretkeymysecretkeymysecretkey123",
                1000 * 60 * 60   // 1 hour
        );
    }

    // 👤 UserDetailsService
    @Bean
    public CustomUserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    // 🔑 Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔐 JWT filter
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    // 🔒 Security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtFilter) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Swagger
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Auth APIs
                .requestMatchers("/auth/**").permitAll()

                // Everything else needs auth
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
