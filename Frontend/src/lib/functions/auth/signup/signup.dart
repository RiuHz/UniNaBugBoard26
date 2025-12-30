import 'package:amazon_cognito_identity_dart_2/cognito.dart';
import 'package:test_app/functions/auth/user_pool.dart';

Future<bool> signUpUser(String email, String password, String name, String surname) async { // Questa è long paramter list
  try {
    await userPool.signUp(
      email,
      password,
      userAttributes: [
        AttributeArg(
          name: 'family_name',
          value: name
        ),
        AttributeArg(
          name: 'given_name',
          value: surname
        )
      ]
    );
  } catch (error) {
    return false;
  }

  return true;
}
