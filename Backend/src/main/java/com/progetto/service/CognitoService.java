package com.progetto.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Service
public class CognitoService {

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    private CognitoIdentityProviderClient getClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public String registraUtente(SignUpRequest utente) {
        CognitoIdentityProviderClient cognitoProvider = getClient();

        try {
            inviaRegistrazione(cognitoProvider, utente)

            confermaRegistrazioneUtente(cognitoProvider, utente);

            verificaEmailUtente(cognitoProvider, utente);

            aggiungiUtenteAlGruppo(cognitoProvider, utente);

            return "Utente creato, confermato, verificato e aggiunto al gruppo '" + groupName + "' con successo.";

        } catch (CognitoIdentityProviderException e) {
            return "Errore AWS: " + e.awsErrorDetails().errorMessage();
        } finally {
            cognitoProvider.close();
        }
    }

    private void verificaEmailUtente(CognitoIdentityProviderClient cognitoProvider, SignUpRequest utente) {
        AttributeType verificaEmail = AttributeType.builder()
        .name("email_verified").value("true").build();

        AdminUpdateUserAttributesRequest richiestaUpdateEmail = AdminUpdateUserAttributesRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .userAttributes(verificaEmail)
            .build();

        cognitoProvider.adminUpdateUserAttributes(richiestaUpdateEmail);
    }

    private void confermaRegistrazioneUtente(CognitoIdentityProviderClient cognitoProvider, SignUpRequest utente) {
        AdminConfirmSignUpRequest richiestaConfermaRegistrazione = AdminConfirmSignUpRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .build();

        cognitoProvider.adminConfirmSignUp(richiestaConfermaRegistrazione);
    }

    private void aggiungiUtenteAlGruppo(CognitoIdentityProviderClient cognitoProvider, SignUpRequest utente) {
        AdminAddUserToGroupRequest richiestaAggiuntaGruppo = AdminAddUserToGroupRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .groupName(utente.getRuolo())
            .build();

        cognitoProvider.adminAddUserToGroup(richiestaAggiuntaGruppo);
    }

    private void inviaRegistrazione(CognitoIdentityProviderClient cognitoProvider, SignUpRequest utente) {
        SignUpRequest richiestaRegistrazione = SignUpRequest.builder()
            .clientId(clientId)
            .username(utente.getEmail())
            .password(utente.getPassword())
            .userAttributes(
                AttributeType.builder().name("email").value(utente.getEmail()).build(),
                AttributeType.builder().name("given_name").value(utente.getNome()).build(),
                AttributeType.builder().name("family_name").value(utente.Cognome()).build()
            )
            .build();

        cognitoProvider.signUp(richiestaRegistrazione);
    }
}