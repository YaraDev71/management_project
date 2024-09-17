package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.UserDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.KeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keycloak/users")
public class KeycloakController {

    private final KeycloakService keycloakService;

    public KeycloakController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @GetMapping
    public List<UserRepresentation> getUsers() {
        return keycloakService.getUsers();
    }

    @GetMapping("/{username}")
    public UserRepresentation getUser(@PathVariable String username) {
        return keycloakService.getUser(username);
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        keycloakService.createUser(userDTO);
    }
}