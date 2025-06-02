package com.teammanager.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class GitHubTokenAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String githubToken = request.getHeader("X-Github-Token");
        
        if (githubToken != null) {
            // Create authentication token with admin role for GitHub Codespace users
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "codespace-user",
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
            
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(request, response);
    }
} 