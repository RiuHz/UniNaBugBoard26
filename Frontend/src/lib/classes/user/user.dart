class User {
  final String name;
  final String surname;

  const User({
    required this.name,
    required this.surname
  });

  static User fromJson(Map<String, dynamic> json) => User(
      name: json['Name'] as String,
      surname: json['Surname'] as String
    );
}
