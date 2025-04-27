package com.islam.userservice.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Component
public class CorsHandler implements CorsConfigurationSource {

    // This method is used to obtain the CORS configuration based on the HTTP request
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        // Create a new CorsConfiguration object
        CorsConfiguration returnValue = new CorsConfiguration();

        // Allow all origins using a wildcard ("*")
        returnValue.setAllowedOriginPatterns(Arrays.asList("*"));

        // Allow credentials (cookies) to be sent with requests
        returnValue.setAllowCredentials(true);

        // Define which headers are allowed
        returnValue.setAllowedHeaders(Arrays.asList(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Origin",
                "Cache-Control",
                "Content-Type",
                "Authorization"
        ));

        // Define which HTTP methods are allowed
        returnValue.setAllowedMethods(Arrays.asList(
                "DELETE",
                "GET",
                "POST",
                "PATCH",
                "PUT"
        ));

        // Return the configuration
        return returnValue;
    }
}

