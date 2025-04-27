package com.islam.userservice.security;

import com.islam.userservice.converter.TempConverter;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.islam.userservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TempConverter converter;

    // This method is called once per request and is used to filter incoming requests
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get the Authorization header from the request
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // Check if the Authorization header contains a Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the JWT token from the header
            jwt = authorizationHeader.substring(7);
            try {
                // Extract the email (username) from the JWT token
                email = jwtUtil.extractUsername(jwt);
            } catch (Exception ex) {
                // Log an error if token extraction fails
                Error error = new Error("Destroyed token");
                System.out.println(error);
            }
        }

        // If email is not null and there is no current authentication in the security context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Retrieve the user details using the email
            UserDTO userDto = this.userService.getUserByEmail(email);
            UserDetails userDetails = converter.userDtoToEntity(userDto);
            // Validate the JWT token
            if (jwtUtil.validateToken(jwt, userDetails)) {
                // Create an authentication token and set it in the security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}

