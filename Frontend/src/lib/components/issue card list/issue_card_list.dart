import 'package:flutter/material.dart';
import 'package:test_app/classes/issue%20fetch%20request/issue_fetch_request.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/components/filter%20bar/filter_bar.dart';
import 'package:test_app/components/issue%20card/issue_card.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/enum/issue/issue_state.dart';
import 'package:test_app/enum/issue/issue_type.dart';
import 'package:test_app/functions/issue/issue.dart';

class IssueCardList extends StatefulWidget {
  final String userId;
  
  const IssueCardList({
    super.key,
    this.userId = ''
  });

  @override
  State<StatefulWidget> createState() => IssueCardListState();
}

class IssueCardListState extends State<IssueCardList> {

  Widget issueList = const CircularProgressIndicator();

  @override
  void initState() {
    super.initState();
    updateIssueList(
      IssueFetchRequest(
        priority: IssuePriority.all,
        type: IssueType.all,
        state: IssueState.all
      )
    );
  }
  
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        FilterBar(
          updateIssueList: updateIssueList,
        ),
        issueList
      ],
    );
  }

  void updateIssueList(IssueFetchRequest request) {
    setState(() {
      issueList = FutureBuilder<List<Issue>> (
        future: fetchIssues(widget.userId, request),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const CircularProgressIndicator();
          }
          
          if (snapshot.hasError) {
            return Text('Ops... Qualcosa è andato storto');
          }
          
          if (snapshot.hasData) {
            return Expanded(
              child: buildIssueCardList(snapshot.data!)
            );
          } 

          return const Text('Non sono presenti Issue al momento!');
        }
      );
    });
  }

  Widget buildIssueCardList(List<Issue> issues) => ListView.builder(
    itemCount: issues.length,
    itemBuilder: (context, index) {
      final Issue issue = issues[index];

      return IssueCard(
        issue: issue
      );
    }
  );
}
