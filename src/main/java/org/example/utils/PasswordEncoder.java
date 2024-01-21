package org.example.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        String plainPassword = "pass123";

        // Create a BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hash the password
        String hashedPassword = passwordEncoder.encode(plainPassword);

        // Print the hashed password
        System.out.println("Original Password: " + plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify a password against its hash
        boolean isPasswordCorrect = passwordEncoder.matches("pass123", hashedPassword);
        System.out.println("Password Verification: " + isPasswordCorrect);
    }
}
