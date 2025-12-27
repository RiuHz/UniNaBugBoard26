import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/main.dart';

class Bug extends Issue {
  Bug() : super(
    icon: const Icon(Icons.pest_control) 
  );

  @override
  bool isEditable(BuildContext context) {
    if (Provider.of<UniNaBugBoard26State>(context).user.role == 'Admin') {
      return true;
    }

    if (user.id == Provider.of<UniNaBugBoard26State>(context).user.id) {
      return true;
    }

    return false;
  }
}
