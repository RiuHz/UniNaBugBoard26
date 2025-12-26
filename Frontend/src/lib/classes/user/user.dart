class User {
  final String name;
  final String surname;

  const User({
    required this.name,
    required this.surname
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      name: json['Name'] as String,
      surname: json['Surname'] as String
    );
  }
}
