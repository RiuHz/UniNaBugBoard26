import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

class Question extends Issue {
  Question() : super(
    icon: const Icon(Icons.question_mark) 
  );

  @override
  bool isEditable(BuildContext context) {
    return false;
  }

  @override
  void getDetailPage(BuildContext context) {
    throw Exception('Detail page not avaiable');
  }
}
