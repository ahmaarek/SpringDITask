package com.sumerge.ahmed.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ValidationHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("x-validation-report");

        String uri = request.getRequestURI();
        boolean isPublicGet = "GET".equalsIgnoreCase(request.getMethod()) &&
                (uri.matches("/api/courses(/by-author/.*)?") || uri.matches("/api/courses(/[^/]+)?"));

        if (isPublicGet || "true".equalsIgnoreCase(header)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Missing or invalid x-validation-report header");
        }
    }
}
