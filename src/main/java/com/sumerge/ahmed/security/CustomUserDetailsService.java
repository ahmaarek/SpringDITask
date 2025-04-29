package com.sumerge.ahmed.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class CustomUserDetailsService {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails student = User.withUsername("student")
                .password("{noop}student123")
                .roles("STUDENT")
                .build();

        UserDetails author = User.withUsername("author")
                .password("{noop}author123")
                .roles("AUTHOR")
                .build();

        return new InMemoryUserDetailsManager(admin, student, author);
    }
}
