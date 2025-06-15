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

    /*
     * Replace the username and password below with proper credentials
     * Username: Choose a username
     * Password: Run PasswordEncoderUtil to encode password and paste encoded password below
     * 
     * REMINDER: Remove the raw password after the encoding is complete!!
     */
    @Bean
    public UserDetailsService userDetailsService() {

        /*
         * Note the Builder design pattern being used here.
         * This is a very powerful design pattern that can be used
         * on more complex classes where many overrides would usually
         * be required to allow for more customizable objects.
         */
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

    /*
     * CSRF is Cross-Site Request Forgery. We have this disabled for development.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Modern way to disable CSRF - Enable for a prod environment
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/graphiql").permitAll() // Allow graphiql
                .anyRequest().authenticated()                         // Require login for everything else, including /graphql
            )
            .formLogin(form -> form
            .defaultSuccessUrl("/graphiql", true)); // Redirect to GraphiQL always after login

        return http.build();
    }
}
