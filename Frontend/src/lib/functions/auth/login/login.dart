import 'package:amazon_cognito_identity_dart_2/cognito.dart';
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/functions/auth/user_pool.dart';

void logInUser(String email, String password) async { // Cambiare il valore come LoggedUser
  final cognitoUser = CognitoUser(email, userPool);
  final authDetails = AuthenticationDetails(
    username: email,
    password: password
  );

  CognitoUserSession? session;

  try {
    session = await cognitoUser.authenticateUser(authDetails);
  } on CognitoClientException catch (error) {
    print(error); // Questo deve essere un pop-up per avvisare l'utente
  }

  session!.getAccessToken().jwtToken; // Da qua bisogna ricavare poi il LoggedUser
}