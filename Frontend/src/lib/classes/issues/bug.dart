import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/enum/issue/issue_state.dart';
import 'package:test_app/enum/user/user_role.dart';
import 'package:test_app/main.dart';
import 'package:test_app/pages/bug%20detail%20page/bug_detail_page.dart';

class Bug extends Issue {
  Bug() : super(
    icon: const Icon(Icons.pest_control) 
  );

  @override
  bool isEditable(BuildContext context) {
    if (state == IssueState.resolved || state == IssueState.todo) {
      return false;
    }

    if (Provider.of<UniNaBugBoard26State>(context).user.role == UserRole.admin) {
      return true;
    }

    if (Provider.of<UniNaBugBoard26State>(context).user.id == user.id) {
      return true;
    }

    return false;
  }

  @override
  void getDetailPage(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (BuildContext context) => BugDetailPage(issue: this)
      )
    );
  }
}
