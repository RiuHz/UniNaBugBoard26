package com.progetto.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.progetto.model.RichiestaRegistrazione;
import com.progetto.interfaces.UserRegistration;

import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;

@Service
public class AmazonWebServiceCognito implements UserRegistration {

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
    
    @Override
    public String registraUtente(RichiestaRegistrazione utente) {
        CognitoIdentityProviderClient cognitoProvider = getClient();

            try {
                inviaRegistrazione(cognitoProvider, utente);

                confermaRegistrazioneUtente(cognitoProvider, utente);

                verificaEmailUtente(cognitoProvider, utente);

                aggiungiUtenteAlGruppo(cognitoProvider, utente);

                return "Utente creato, confermato, verificato e aggiunto al gruppo '" + utente.getRuolo() + "' con successo.";

            } catch (CognitoIdentityProviderException e) {
                return "Errore AWS: " + e.awsErrorDetails().errorMessage();
            } finally {
                cognitoProvider.close();
        }
    }

    private CognitoIdentityProviderClient getClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    private void verificaEmailUtente(CognitoIdentityProviderClient cognitoProvider, RichiestaRegistrazione utente) {
        AttributeType verificaEmail = AttributeType.builder()
        .name("email_verified").value("true").build();

        AdminUpdateUserAttributesRequest richiestaUpdateEmail = AdminUpdateUserAttributesRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .userAttributes(verificaEmail)
            .build();

        cognitoProvider.adminUpdateUserAttributes(richiestaUpdateEmail);
    }

    private void confermaRegistrazioneUtente(CognitoIdentityProviderClient cognitoProvider, RichiestaRegistrazione utente) {
        AdminConfirmSignUpRequest richiestaConfermaRegistrazione = AdminConfirmSignUpRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .build();

        cognitoProvider.adminConfirmSignUp(richiestaConfermaRegistrazione);
    }

    private void aggiungiUtenteAlGruppo(CognitoIdentityProviderClient cognitoProvider, RichiestaRegistrazione utente) {
        AdminAddUserToGroupRequest richiestaAggiuntaGruppo = AdminAddUserToGroupRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .groupName(utente.getRuolo())
            .build();

        cognitoProvider.adminAddUserToGroup(richiestaAggiuntaGruppo);
    }

    private void inviaRegistrazione(CognitoIdentityProviderClient cognitoProvider, RichiestaRegistrazione utente) {
        SignUpRequest richiestaRegistrazione = SignUpRequest.builder()
            .clientId(clientId)
            .username(utente.getEmail())
            .password(utente.getPassword())
            .userAttributes(
                AttributeType.builder().name("email").value(utente.getEmail()).build(),
                AttributeType.builder().name("given_name").value(utente.getNome()).build(),
                AttributeType.builder().name("family_name").value(utente.getCognome()).build()
            )
            .build();

        cognitoProvider.signUp(richiestaRegistrazione);
    }
}
