import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

class IssueCard extends StatelessWidget {
  final Issue issue;

  const IssueCard({
    super.key,
    required this.issue
  });

  // Fa schifo sta roba...

  @override
  Widget build(BuildContext context) {
    return Card(
      surfaceTintColor: Theme.of(context).colorScheme.inverseSurface,
      child: Padding(
        padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
        child: SizedBox(
          height: 100,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              AspectRatio(aspectRatio: 1.0, child: issue.icon),
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.fromLTRB(20.0, 0.0, 2.0, 0.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        issue.title,
                        maxLines: 2,
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
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(20.0, 0.0, 2.0, 0.0),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Stato: ${issue.state}',
                      style: const TextStyle(fontSize: 12.0)
                    ),
                    const Padding(padding: EdgeInsets.only(bottom: 2.0)),
                    Expanded(
                      child: Text(
                        'Priorità: ${issue.priority}',
                        style: const TextStyle(fontSize: 12.0)
                      ),
                    ),
                    Text('Bottone')
                  ],
                )
              )
            ],
          ),
        ),  
      )
    );
  }

}
