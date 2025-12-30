import 'package:flutter/material.dart';
import 'package:test_app/components/form/add%20issue/add_issue_form.dart';
import 'package:test_app/components/navbar/navbar.dart';

class AddIssuePage extends StatelessWidget{
  const AddIssuePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Navbar(),
        Expanded(
          child: AddIssueForm()
        )
      ]
    );
  }
}
