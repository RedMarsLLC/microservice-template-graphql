package com.redmars.microservice_template_graphql.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* 
 * Run the following commands to encrypt your password!
 * mvn compile
 * mvn exec:java -Dexec.mainClass="com.redmars.microservice_template_graphql.utils.PasswordEncoderUtil"
 */
public class PasswordEncoderUtil {
    public static void main(String[] args) {
        String rawPassword = "superSecretPassword";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(rawPassword);
        System.out.println("Encrypted Password: ");
        System.out.println(hashed);
    }
}
