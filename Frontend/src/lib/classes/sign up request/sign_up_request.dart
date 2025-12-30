import 'dart:convert';
import 'package:test_app/enum/user/user_role.dart';

class SignUpRequest {
  final String email;
  final String password;
  final String name;
  final String surname;
  final UserRole role;

  const SignUpRequest(
    this.email,
    this.password,
    this.name,
    this.surname,
    this.role
  );

  String getJsonData() => json.encode(
    {
      "email": email,
      "password": password,
      "nome":  name,
      "cognome": surname,
      "ruolo": role.type,
    }
  );
}
