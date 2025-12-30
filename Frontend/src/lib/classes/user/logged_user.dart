import 'package:test_app/classes/user/user.dart';
import 'package:test_app/enum/user/user_role.dart';

class LoggedUser extends User {
  final UserRole role;
  final String token;

  const LoggedUser({
    required super.id,
    required super.name,
    required super.surname,
    required this.role,
    required this.token
  });
}
