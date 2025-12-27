import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

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
        title: Text('Modifica Issue')
      ),
      body: Center(
        child: Hero(
          tag: 'Issue Detail',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  issue.title,
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold
                  ),
                )  
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Descrizione',
                      style: TextStyle(
                        fontSize: 24
                      )  
                    ),
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
                  'Stato: ${issue.state}',
                  style: TextStyle(
                    fontSize: 22
                  )    
                ) 
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  'Priorità: ${issue.priority}',
                  style: TextStyle(
                    fontSize: 22
                  )  
                )
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: () {}, // Qui va segnata l'issue come resolved e va poi chiuso l'Hero (la finestra che apre i dettagli)
                    style: ElevatedButton.styleFrom(
                      shadowColor: Theme.of(context).colorScheme.inverseSurface,
                      shape: RoundedRectangleBorder(
                        side: BorderSide(
                          color: Theme.of(context).colorScheme.inverseSurface,
                        ),
                        borderRadius: BorderRadiusGeometry.circular(25)
                      )
                    ),
                    child: Text(
                      'Segna come Resolved',
                      style: TextStyle(
                        color: Theme.of(context).colorScheme.inverseSurface,
                        fontSize: 16
                      )
                    ),
                  ),
                ],
              )
            ],
          ),
        )
      )
    );
  }
}