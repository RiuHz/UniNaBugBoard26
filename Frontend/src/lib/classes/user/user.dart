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
      id: json['id'] as String? ?? '',
      name: json['nome'] as String? ?? '',
      surname: json['cognome'] as String? ?? ''
  );
}
