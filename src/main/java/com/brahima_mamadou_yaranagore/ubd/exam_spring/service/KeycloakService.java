package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.UserDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.keycloak.Credentials;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {

    @Value("${app.keycloak.login.url}")
    private String keycloakServerUrl;

    @Value("${app.keycloak.realm.name}")
    private String realm;

    @Value("${app.keycloak.client-id}")
    private String clientId;

    @Value("${app.keycloak.client-secret}")
    private String clientSecret;

    private final Keycloak keycloak;
    @Autowired
    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<UserRepresentation> getUsers() {
        return keycloak.realm(realm).users().list();
    }

    public UserRepresentation getUser(String username) {
        return keycloak.realm(realm).users().search(username, true).stream().findFirst().orElse(null);
    }

    public void createUser(UserDTO userDTO) {
        CredentialRepresentation credential =
                Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(true);

        keycloak.realm(realm).users().create(user);

        // Ajout de la gestion des rôles
        if (!userDTO.getRoles().isEmpty()) {
            UserResource userResource = keycloak.realm(realm).users().get(user.getId());

            ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);

            for (String role : userDTO.getRoles()) {
                RoleRepresentation roleRepresentation = keycloak.realm(realm)
                        .clients()
                        .get(client.getId())
                        .roles()
                        .get(role)
                        .toRepresentation();
                userResource.roles().clientLevel(client.getId()).add(Arrays.asList(roleRepresentation));
            }
        }
    }
}