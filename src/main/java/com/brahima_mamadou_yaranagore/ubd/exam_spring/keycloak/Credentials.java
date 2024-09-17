package com.brahima_mamadou_yaranagore.ubd.exam_spring.keycloak;

import org.keycloak.representations.idm.CredentialRepresentation;

public class Credentials {
    private Credentials(){}
    public static CredentialRepresentation createPasswordCredentials(String password){

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);

        return credentialRepresentation;
    }
}
