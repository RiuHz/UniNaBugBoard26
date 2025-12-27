import 'package:flutter/material.dart';
import 'package:test_app/enum/issue_priority.dart';
import 'package:test_app/components/text%20input%20field/text_input_field.dart';

class AddIssuePage extends StatefulWidget{
  const AddIssuePage({super.key});

  @override
  State<StatefulWidget> createState() => AddIssuePageState();
}

class AddIssuePageState extends State<AddIssuePage> { // Dovrebbero prendere il context da qui?
  IssuePriority? selectedPriority;
  String? title;
  String? description;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 25.0, horizontal: 50.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.symmetric(vertical: 10.0),
            child: TextInputField(
              title: 'Titolo',
            ),  
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 10.0),
            child: TextInputField(
              title: 'Descrizione',
            ),  
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 10.0),
            child: Text(
              'Stato: ToDo',
              style: TextStyle(
                fontSize: 16.0
              ),  
            ) 
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 10.0),
            child: DropdownMenu<IssuePriority>(
              dropdownMenuEntries: IssuePriority.entries.where((priority) => priority.value != IssuePriority.all).toList(),
              label: const Text('Priorità'),
              initialSelection: IssuePriority.low,
              requestFocusOnTap: false,
                onSelected: (IssuePriority? state) {
                setState(() {
                  selectedPriority = state;
                  // Qui vanno fatte le chiamate per i filtri che cambiano le issue mostrate
                });
              },
            ), 
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {}, // Questa sarà la funzione di conferma
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
                  'Crea Issue',
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
    );
  }
}
