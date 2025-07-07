package com.example.events.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Manoel Campos
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                       auth
                           .requestMatchers(HttpMethod.GET, "/", "/index.html", "/conference", "/conference/{id}").permitAll()
                           .anyRequest().authenticated() // Exige auth a qualquer outra requisição
                )
                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
