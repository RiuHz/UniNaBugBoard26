package com.progetto.aws.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.progetto.exception.AuthException;
import com.progetto.interfaces.UserRegistration;
import com.progetto.models.RichiestaRegistrazione;
import com.progetto.models.Utente;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

@Service
public class AmazonWebServiceCognito implements UserRegistration {

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Autowired
    private CognitoIdentityProviderClient cognitoProvider;

    @Override
    public String registraUtente(RichiestaRegistrazione utente) throws AuthException {
            try {
                inviaRegistrazione(utente);

                confermaRegistrazioneUtente(utente);

                verificaEmailUtente(utente);

                aggiungiUtenteAlGruppo(utente);

                return "Utente creato, confermato, verificato e aggiunto al gruppo '" + utente.getRuolo() + "' con successo.";

            } catch (CognitoIdentityProviderException e) {
                throw new AuthException();
            }
    }

    private void verificaEmailUtente(RichiestaRegistrazione utente) {
        AttributeType verificaEmail = AttributeType.builder()
            .name("email_verified")
            .value("true")
            .build();

        AdminUpdateUserAttributesRequest richiestaUpdateEmail = AdminUpdateUserAttributesRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .userAttributes(verificaEmail)
            .build();

        cognitoProvider.adminUpdateUserAttributes(richiestaUpdateEmail);
    }

    private void confermaRegistrazioneUtente(RichiestaRegistrazione utente) {
        AdminConfirmSignUpRequest richiestaConfermaRegistrazione = AdminConfirmSignUpRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .build();

        cognitoProvider.adminConfirmSignUp(richiestaConfermaRegistrazione);
    }

    private void aggiungiUtenteAlGruppo(RichiestaRegistrazione utente) {
        AdminAddUserToGroupRequest richiestaAggiuntaGruppo = AdminAddUserToGroupRequest.builder()
            .userPoolId(userPoolId)
            .username(utente.getEmail())
            .groupName(utente.getRuolo())
            .build();

        cognitoProvider.adminAddUserToGroup(richiestaAggiuntaGruppo);
    }

    private void inviaRegistrazione(RichiestaRegistrazione utente) {
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

    public Utente recuperaInfomazioniUtente(String sub) throws AuthException {
        try {
            List<AttributeType> listaAttributiUtente = getAttributiUtente(sub);

            Utente user = new Utente();
            
            user.setId(sub);
            user.setNome(getAttributoUtente(listaAttributiUtente, "given_name"));
            user.setCognome(getAttributoUtente(listaAttributiUtente, "family_name"));

            return user;
        } catch (CognitoIdentityProviderException e) {
            throw new AuthException();
        }
    }

    private String getAttributoUtente(List<AttributeType> attributiUtente, String nomeAttributo) {
        return attributiUtente.stream()
            .filter(attributo -> attributo.name().equals(nomeAttributo))
            .findFirst()
            .map(AttributeType::value)
            .orElse(null);
    }

    private List<AttributeType> getAttributiUtente(String sub) {
        ListUsersRequest request = ListUsersRequest.builder()
            .userPoolId(userPoolId)
            .filter("sub = \"" + sub + "\"")
            .limit(1)
            .build();

        ListUsersResponse response = cognitoProvider.listUsers(request);

        return response.users().get(0).attributes();
    }
}
