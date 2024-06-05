
package com.example.SystemAssistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
//                .anyRequest().authenticated()
//            .formLogin().disable();
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
                // Allow access to these endpoints                        // without authentication
                        
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//                // Ensuring that the SecurityContextHolder is always cleared
//                http.addFilterBefore((request, response, chain) -> {
//                    try {
//                        chain.doFilter(request, response);
//                    } finally {
//                        SecurityContextHolder.clearContext();
//                    }
//                }, UsernamePasswordAuthenticationFilter.class);
                
		return http.build();
	}
    
    
}

