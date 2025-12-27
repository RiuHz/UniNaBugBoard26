import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

class Feature extends Issue {
  Feature() : super(
    icon: const Icon(Icons.bookmark_add)
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
