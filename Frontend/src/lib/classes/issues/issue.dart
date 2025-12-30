import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/bug.dart';
import 'package:test_app/classes/issues/documentation.dart';
import 'package:test_app/classes/issues/feature.dart';
import 'package:test_app/classes/issues/question.dart';
import 'package:test_app/classes/user/user.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/enum/issue/issue_state.dart';

abstract class Issue {
  final Icon icon;
  late int id;
  late User user;
  late String title;
  late String description;
  late IssuePriority priority;
  late IssueState state;
  late String image;

  Issue({
    required this.icon
  });

  static Issue fromJson(Map<String, dynamic> json) {
    Issue issue = IssueFactory.fromType(json['Tipo'] as String);

    issue.id = json['id'] as int;
    issue.user = User.fromJson(json['utente']);
    issue.title = json['titolo'] as String;
    issue.description = json['descrizione'] as String;
    issue.priority = IssuePriority.fromString(json['priorita'] as String);
    issue.state = IssueState.fromString(json['stato'] as String);
    issue.image = json['immagine'] as String? ?? '';

    return issue;
  }

  bool isEditable(BuildContext context);

  void getDetailPage(BuildContext context);
}

class IssueFactory {
  static Issue fromType(String type) {
      switch (type) {
        case 'Bug':
          return Bug();
        case 'Documentation':
          return Documentation();
        case 'Question':
          return Question();
        case 'Feature':
          return Feature();
      }

      throw Exception('No issue type found...');
  }
}
