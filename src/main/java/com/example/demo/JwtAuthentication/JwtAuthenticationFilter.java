package com.example.demo.JwtAuthentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //TODO implement JwtService
    //private final JwtService jwtService;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        //Check if the jwt header is correct and not null
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //TODO continue on TokenAuthenticationFilter
        jwt = authenticationHeader.substring(7);
        //userEmail = jwtService.extractUserEmail(jwt);

    }
}
