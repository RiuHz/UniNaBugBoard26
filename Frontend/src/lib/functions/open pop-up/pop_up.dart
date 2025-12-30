import 'package:flutter/material.dart';

void openPopUp(BuildContext context, bool successful, String successfulTitle, String successfulMessage) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
      title: Text(successful ? successfulTitle : 'Errore...'),
        content: Text(successful ? successfulMessage : 'Ops... qualcosa è andato storto...'),
        actions: <Widget>[
          TextButton(
            onPressed: () {
              Navigator.of(context).pop();
            },
            child: Text(
              'Chiudi',
              style: TextStyle(color: Theme.of(context).colorScheme.inverseSurface),
            ),
          )
        ]
      );
    }
  );
}
