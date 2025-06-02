package com.teammanager.controller;

import com.teammanager.dto.LoginRequest;
import com.teammanager.dto.LoginResponse;
import com.teammanager.dto.UserDTO;
import com.teammanager.model.User;
import com.teammanager.service.UserService;
import com.teammanager.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EntityMapper mapper;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            User user = userService.getUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Create Basic Auth token for Budibase
            String token = Base64.getEncoder().encodeToString(
                (loginRequest.getUsername() + ":" + loginRequest.getPassword()).getBytes()
            );
            
            return ResponseEntity.ok(new LoginResponse(
                "Basic " + token,
                mapper.toUserDTO(user)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        
        return userService.getUserByUsername(auth.getName())
            .map(user -> ResponseEntity.ok(mapper.toUserDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("Logged out successfully");
    }
} 