import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/components/issue%20card%20list/issue_card_list.dart';
import 'package:test_app/components/navbar/navbar.dart';
import 'package:test_app/main.dart';

class MyBugsPage extends StatelessWidget {
  final IssueCardList issueCardList = const IssueCardList();

  const MyBugsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Navbar(),
        Expanded(
          child:IssueCardList(
            userId: Provider.of<UniNaBugBoard26State>(context).user.id
          )
        )
      ]
    );
  }
}
