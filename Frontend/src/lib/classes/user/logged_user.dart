import 'package:test_app/classes/user/user.dart';
import 'package:test_app/enum/user/user_role.dart';

class LoggedUser extends User { // Potrei prendere tutto dal token JWT? Convertire da fromString il ruolo
  final UserRole role;
  final String token;

  const LoggedUser({
    required super.id,
    required super.name,
    required super.surname,
    required this.role,
    required this.token
  });

  static LoggedUser fromJson(Map<String, dynamic> json) => LoggedUser( // Il JWT viene preso come token?
    id: json['ID'] as String? ?? '',
    name: json['Nome'] as String? ?? '',
    surname: json['Cognome'] as String? ?? '',
    role: UserRole.fromString(json['Ruolo'] as String),
    token: json['Token']
  );
}
