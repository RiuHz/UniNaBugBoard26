import 'package:flutter/material.dart';
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
      id: json['Id'] as int,
      user: User.fromJson(json['User']),
      title: json['Title'] as String,
      description: json['Description'] as String,
      priority: json['Priority'] as String,
      state: json['State'] as String,
      type: json['Type'] as String,
      images: List<String>.from(json['Images'] as List<dynamic>)
    );

    static Card toCard(BuildContext context, Issue issue) => Card(
      surfaceTintColor: Theme.of(context).colorScheme.inverseSurface,
      shadowColor: Theme.of(context).colorScheme.secondary,
      child: ListTile(
        leading: Issue.selectIssueIcon(issue.type),
        title: Text(issue.title),
        subtitle: Text(issue.description),
      )
    );

    // Qua bisogna usare la OOP (Code smell assurda sto switch, ma per ora deve funzionare :D)
    static Icon selectIssueIcon(String issueType) {
      switch (issueType) {
        case 'Bug':
          return Icon(Icons.pest_control);
        case 'Documentation':
          return Icon(Icons.edit_document);
        case 'Question':
          return Icon(Icons.question_mark);
        case 'Feature':
          return Icon(Icons.bookmark_add);
      }

      throw Exception('No icon found for this issue...');
    }
}
