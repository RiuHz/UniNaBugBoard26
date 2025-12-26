import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

class Documentation extends Issue {
  Documentation() : super(
    icon: const Icon(Icons.edit_document) 
  );

  @override
  bool isEditable() {
    return false;
  }
}
