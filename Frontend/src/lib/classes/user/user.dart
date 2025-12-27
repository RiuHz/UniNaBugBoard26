class User {
  final String id;
  final String name;
  final String surname;
  final String role;

  const User({
    required this.id,
    required this.name,
    required this.surname,
    required this.role,
  });

  static User fromJson(Map<String, dynamic> json) => User(
      id: json['Id'] as String? ?? '',
      name: json['Name'] as String? ?? '',
      surname: json['Surname'] as String? ?? '',
      role: json['Role'] as String? ?? ''
    );
}
