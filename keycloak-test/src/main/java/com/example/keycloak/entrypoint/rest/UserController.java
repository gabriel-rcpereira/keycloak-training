package com.example.keycloak.entrypoint.rest;

import com.example.keycloak.config.SecurityContextUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/username")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<String> getAuthorizationUsername() {
        return ResponseEntity.ok(SecurityContextUtils.getUserName());
    }
}
