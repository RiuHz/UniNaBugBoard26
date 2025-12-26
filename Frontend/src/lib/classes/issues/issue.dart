import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/bug.dart';
import 'package:test_app/classes/issues/documentation.dart';
import 'package:test_app/classes/issues/feature.dart';
import 'package:test_app/classes/issues/question.dart';
import 'package:test_app/classes/user/user.dart';

abstract class Issue {
  final Icon icon;
  late int id;
  late User user;
  late String title;
  late String description;
  late String priority;
  late String state;
  late List<String> images;

  Issue({
    required this.icon
  });

  static Issue fromJson(Map<String, dynamic> json) {
    Issue issue = IssueFactory.fromType(json['Type'] as String);

    issue.id = json['Id'] as int;
    issue.user = User.fromJson(json['User']);
    issue.title = json['Title'] as String;
    issue.description = json['Description'] as String;
    issue.priority = json['Priority'] as String;
    issue.state = json['State'] as String;
    issue.images = List<String>.from(json['Images'] as List<dynamic>);

    return issue;
  }

  bool isEditable();
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
