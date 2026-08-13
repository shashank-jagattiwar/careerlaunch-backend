 package com.careerlaunch.backend.config;

import com.careerlaunch.backend.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // ============================
                        // PUBLIC AUTH APIs
                        // ============================

                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        // ============================
                        // USER APIs
                        // ============================

                        // USER and ADMIN can access their profile
                        .requestMatchers("/api/v1/user/**")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/api/v1/saved-jobs/**")
                        .hasAnyRole("USER", "ADMIN")

                        // ============================
                        // JOB APIs
                        // ============================

                        // Anyone can browse jobs
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/jobs/**"
                        )
                        .permitAll()

                        // Only ADMIN can create jobs
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/v1/jobs/**"
                        )
                        .hasRole("ADMIN")

                        // Only ADMIN can update jobs
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/v1/jobs/**"
                        )
                        .hasRole("ADMIN")

                        // Only ADMIN can delete jobs
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/v1/jobs/**"
                        )
                        .hasRole("ADMIN")

                        // ============================
                        // ADMIN APIs
                        // ============================

                        .requestMatchers("/api/v1/admin/**")
                        .hasRole("ADMIN")

                        // ============================
                        // EVERYTHING ELSE
                        // ============================

                        .anyRequest()
                        .authenticated()
                )

                // ============================
                // JWT FILTER
                // ============================

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
