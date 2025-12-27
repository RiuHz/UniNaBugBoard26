import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/classes/user/user.dart';
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
              IssueCardIcon(icon: issue.icon),
              IssueCardInfo(
                title: issue.title,
                description: issue.description,
                user: issue.user
              ),
              IssueCardState(
                state: issue.state,
                priority: issue.priority,
                isEditable: issue.isEditable(context)
              )
            ],
          ),
        ),  
      )
    );
  }

  Color selectColorFromPriority(BuildContext context, String priority) {
    switch (priority) {
      case 'Low':
        return Theme.of(context).green;
      case 'Medium':
        return Theme.of(context).yellow;
      case 'High':
        return Theme.of(context).red;
    }

    throw Exception('Unkown priority for this issue...');
  }

}

class IssueCardIcon extends StatelessWidget {
  final Icon icon;

  const IssueCardIcon({
    super.key,
    required this.icon
  });

  @override
  Widget build(BuildContext context) {
    return AspectRatio(aspectRatio: 1.0, child: icon);
  }
}

class IssueCardInfo extends StatelessWidget {
  final String title;
  final String description;
  final User user;

  const IssueCardInfo({
    super.key,
    required this.title,
    required this.description,
    required this.user
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
              title,
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
            const Padding(padding: EdgeInsets.only(bottom: 2.0)),
            Expanded(
              child: Text(
                description,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: const TextStyle(fontSize: 12.0,),
              ),
            ),
            Text(
              '${user.surname} ${user.name}',
              style: const TextStyle(fontSize: 12.0)
            ),
          ],
        )
      ),
    );
  }
}

class IssueCardState extends StatelessWidget {
  final String state;
  final String priority;
  final bool isEditable;

  const IssueCardState({
    super.key,
    required this.state,
    required this.priority,
    required this.isEditable
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
              'Stato: $state',
              style: const TextStyle(fontSize: 12.0)
            ),
            const Padding(padding: EdgeInsets.only(bottom: 2.0)),
            Expanded(
              child: Text(
                'Priorità: $priority',
                style: const TextStyle(fontSize: 12.0)
              ),
            ),
            if (isEditable)
              ElevatedButton(
                onPressed: () {}, // Questa sarà la funzione per il display della pagina di modifica
                style: ElevatedButton.styleFrom(
                  shadowColor: Theme.of(context).colorScheme.inverseSurface,
                ),
                child: Text(
                  'Modifica',
                  style: TextStyle(color: Theme.of(context).colorScheme.inverseSurface)
                ),
              ),
          ],
        )
      )
    );
  }
}
