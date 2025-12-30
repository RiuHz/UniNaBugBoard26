import 'package:amazon_cognito_identity_dart_2/cognito.dart';
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/enum/user/user_role.dart';
import 'package:test_app/functions/auth/user_pool.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

Future<LoggedUser?> logInUser(String email, String password) async {
  final cognitoUser = CognitoUser(email, userPool);
  final authDetails = AuthenticationDetails(
    username: email,
    password: password
  );

  CognitoUserSession? session;

  try {
    session = await cognitoUser.authenticateUser(authDetails);
  } on CognitoClientException catch (error) {
    print(error);
    return null;
  }

  String jwtToken = session!.getAccessToken().jwtToken as String;

  return getLoggedUserAttributes(cognitoUser, jwtToken);
}

Future<LoggedUser?> getLoggedUserAttributes(CognitoUser user, String jwtToken) async {

  Map<String, dynamic> decoded = JwtDecoder.decode(jwtToken);

  List<CognitoUserAttribute>? attributes;
  
  try {
    attributes = await user.getUserAttributes();
  } catch (e) {
    return null;
  }

  return LoggedUser(
    id: attributes?[4].getValue() as String, 
    name: attributes?[3].getValue() as String, 
    surname: attributes?[2].getValue() as String, 
    role: UserRole.fromString(decoded['cognito:groups'][0] as String), 
    token: jwtToken
  );
}
