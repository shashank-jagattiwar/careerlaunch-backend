package com.careerlaunch.backend.security.filter;

import com.careerlaunch.backend.security.jwt.JwtService;
import com.careerlaunch.backend.security.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // If there is no Bearer token, continue normally
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " from the beginning
        String token = authHeader.substring(7);


        // Extract email from JWT
        String email = jwtService.extractUsername(token);

        System.out.println("Extracted Email: " + email);

        // Check whether authentication already exists
        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user from database
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println(
                    "User Loaded: " + userDetails.getUsername()
            );
            

            // Validate JWT
            if (jwtService.isTokenValid(token, userDetails)) {

                System.out.println("JWT VALID");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // Store authenticated user in SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println(
                        "Authentication stored in SecurityContext"
                );

            } else {

                System.out.println("JWT INVALID");
            }
        }

        // Continue to controller
        filterChain.doFilter(request, response);
    }
}