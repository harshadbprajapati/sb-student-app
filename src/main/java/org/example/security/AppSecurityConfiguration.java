package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(){
        UserDetails sylvester = User.builder()
                .username("sylvester")
                .password("{noop}pass123")
                .roles("STUDENT")
                .build();

        UserDetails tom = User.builder()
                .username("tom")
                .password("{noop}pass123")
                .roles("TEACHER")
                .build();

        UserDetails arnold = User.builder()
                .username("arnold")
                .password("{noop}pass123")
                .roles("TEACHER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(sylvester, tom, arnold);
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


