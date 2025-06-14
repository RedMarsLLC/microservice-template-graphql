package com.redmars.microservice_template_graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        return new InMemoryUserDetailsManager(
        User.builder()
            .username("admin")
            .password("$2a$10$bO2SSN/ZT1xREpdautrMVO.KKC4qwKPyl87baHmvCJ9/MJTpIUudy")
            .roles("USER")
            .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 🔄 Modern way to disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/graphiql", "/graphql").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
            .defaultSuccessUrl("/graphiql", true)); // 👈 redirect to GraphiQL always after login

        return http.build();
    }
}
