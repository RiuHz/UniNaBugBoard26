import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/components/buttons/rounded%20button/rounded_button.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/theme/theme.dart';

class IssueCard extends StatelessWidget {
  final Issue issue;

  const IssueCard({
    super.key,
    required this.issue
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      surfaceTintColor: Theme.of(context).colorScheme.inverseSurface,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(25),
        side: BorderSide(
          color: selectColorFromPriority(context, issue.priority),
          width: 2.0
        )
      ),
      child: Padding(
        padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
        child: SizedBox(
          height: 100,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              IssueCardIcon(issue: issue),
              IssueCardInfo(issue: issue),
              IssueCardState(issue: issue)
            ],
          ),
        ),  
      )
    );
  }

  Color selectColorFromPriority(BuildContext context, IssuePriority priority) {
    switch (priority) {
      case IssuePriority.low:
        return Theme.of(context).green;
      case IssuePriority.medium:
        return Theme.of(context).yellow;
      case IssuePriority.high:
        return Theme.of(context).red;
      default:
        throw Exception('Unkown priority for this issue... $priority');
    }
  }
}

class IssueCardIcon extends StatelessWidget {
  final Issue issue;

  const IssueCardIcon({
    super.key,
    required this.issue
  });

  @override
  Widget build(BuildContext context) {
    return AspectRatio(aspectRatio: 1.0, child: issue.icon);
  }
}

class IssueCardInfo extends StatelessWidget {
  final Issue issue;

  const IssueCardInfo({
    super.key,
    required this.issue
  });

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.fromLTRB(20.0, 0.0, 2.0, 0.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              issue.title,
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
            const Padding(padding: EdgeInsets.only(bottom: 2.0)),
            Expanded(
              child: Text(
                issue.description,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: const TextStyle(fontSize: 12.0,),
              ),
            ),
            Text(
              '${issue.user.surname} ${issue.user.name}',
              style: const TextStyle(fontSize: 12.0)
            ),
          ],
        )
      ),
    );
  }
}

class IssueCardState extends StatelessWidget {
  final Issue issue;

  const IssueCardState({
    super.key,
    required this.issue
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(20.0, 0.0, 2.0, 0.0),
      child: SizedBox(
        width: 125,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              'Stato: ${issue.state.progress}',
              style: const TextStyle(fontSize: 12.0)
            ),
            const Padding(padding: EdgeInsets.only(bottom: 2.0)),
            Expanded(
              child: Text(
                'Priorità: ${issue.priority.level}',
                style: const TextStyle(fontSize: 12.0)
              ),
            ),
            if (issue.isEditable(context))
              RoundedButton(
                text: 'Modifica',
                onPressedFunction: () {issue.getDetailPage(context);}
              )
          ],
        )
      )
    );
  }
}
