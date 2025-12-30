class User {
  final String id;
  final String name;
  final String surname;

  const User({
    required this.id,
    required this.name,
    required this.surname
  });

  static User fromJson(Map<String, dynamic> json) => User(
      id: json['ID'] as String? ?? '',
      name: json['Nome'] as String? ?? '',
      surname: json['Cognome'] as String? ?? ''
  );
}
