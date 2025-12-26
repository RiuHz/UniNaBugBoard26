import 'package:test_app/classes/user/user.dart';

class Issue {
  final int id;
  final User user;
  final String title;
  final String description;
  final String priority;
  final String state;
  final String type;
  final List<String> images;

  const Issue({
    required this.id,
    required this.user,
    required this.title,
    required this.description,
    required this.priority,
    required this.state,
    required this.type,
    required this.images
  });

  static Issue fromJson(Map<String, dynamic> json) => Issue(
      id: json['ID'] as int,
      user: json['User'] as User,
      title: json['Title'] as String,
      description: json['Description'] as String,
      priority: json['Proprity'] as String,
      state: json['State'] as String,
      type: json['Type'] as String,
      images: json['Images'] as List<String>
    );
}
