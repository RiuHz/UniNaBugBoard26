import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/components/rounded%20button/rounded_button.dart';

class BugDetailPage extends StatelessWidget {
  final Issue issue;

  const BugDetailPage({
    super.key,
    required this.issue
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(issue.title)
      ),
      body: Center(
        child: Hero(
          tag: 'Issue Detail',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      issue.description,
                      style: TextStyle(
                        fontSize: 16
                      )  
                    )
                  ],
                )
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  'Stato: ${issue.state.progress}',
                  style: TextStyle(
                    fontSize: 22
                  )    
                ) 
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  'Priorità: ${issue.priority.level}',
                  style: TextStyle(
                    fontSize: 22
                  )  
                )
              ),
              Padding(
                padding: EdgeInsetsGeometry.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text('Nessun immagine prevista'), // Modificare per mostrare l'immagine
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  RoundedButton(
                    text: 'Segna come Resolved',
                    onPressedFunction: markIssueAsResolved
                  )
                ],
              )
            ],
          ),
        )
      )
    );
  }

  void markIssueAsResolved() {
    // Qui va segnata l'issue come resolved e va poi chiuso l'Hero (la finestra che apre i dettagli)
    // Bottone che carica
  }
}
