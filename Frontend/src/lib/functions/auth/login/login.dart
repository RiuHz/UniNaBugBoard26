import 'package:amazon_cognito_identity_dart_2/cognito.dart';
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/functions/auth/user_pool.dart';

Future<LoggedUser?> logInUser(String email, String password) async {
  final cognitoUser = CognitoUser(email, userPool);
  final authDetails = AuthenticationDetails(
    username: email,
    password: password
  );

  CognitoUserSession? session;

  try {
    session = await cognitoUser.authenticateUser(authDetails);
  } on CognitoClientException {
    print('Error');
    return null;
  }

  // Da qua bisogna ricavare poi il LoggedUser da passare al main e il ruolo e capire se servei l JWT (chiedi a Chat) (Il logged user deve essere inizializzato prima?...)
  String jwtToken = session!.getAccessToken().jwtToken as String;

  print(jwtToken);

  return null;
}
