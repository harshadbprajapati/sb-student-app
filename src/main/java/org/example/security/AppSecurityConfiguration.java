package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

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
}


