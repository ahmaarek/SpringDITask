package com.sumerge.ahmed.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/courses", "/api/courses/{name}", "/api/courses/by-author/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/courses").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/courses/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/**").authenticated()
                        .requestMatchers("/api/authors").hasRole("ADMIN")

                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new ValidationHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
