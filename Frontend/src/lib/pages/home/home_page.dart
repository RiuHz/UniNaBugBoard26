import 'package:flutter/material.dart';
import 'package:test_app/components/issue%20card%20list/issue_card_list.dart';
import 'package:test_app/components/navbar/navbar.dart';
import 'package:test_app/functions/issue/issue.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return const Column(
      children: [
        Navbar(),
        Expanded(
          child: IssueCardList(
            getIssueFunction: getIssues,
          )
        )
      ]
    );
  }
}
