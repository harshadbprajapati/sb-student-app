package org.example.security;

import org.example.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class AppSecurityConfiguration {
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    public AppSecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.customUserDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/api/students")
                        .hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/students/**")
                        .hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/students")
                        .hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PATCH,"/api/students/**")
                        .hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/api/students/**")
                        .hasRole("ADMIN")
        );
        // HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF protection
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}


