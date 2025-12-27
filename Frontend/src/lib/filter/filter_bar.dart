import 'package:flutter/material.dart';
import 'package:test_app/filter/enum/issue_priority.dart';
import 'package:test_app/filter/enum/issue_state.dart';
import 'package:test_app/filter/enum/issue_type.dart';

class FilterBar extends StatefulWidget {
  const FilterBar({super.key});

  @override
  State<FilterBar> createState() => FilterBarState();
}

class FilterBarState extends State<FilterBar> {
  IssuePriority? selectedPriority;
  IssueType? selectedType;
  IssueState? selectedState;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 10.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          DropdownMenu<IssuePriority>(
            dropdownMenuEntries: IssuePriority.entries,
            label: const Text('Priorità'),
            initialSelection: null,
            onSelected: (IssuePriority? priority) {
              setState(() {
                selectedPriority = priority;
                // Qui vanno fatte le chiamate per i filtri che cambiano le issue mostrate
              });
            },
          ),
          DropdownMenu<IssueType>(
            dropdownMenuEntries: IssueType.entries,
            label: const Text('Tipo'),
            initialSelection: null,
              onSelected: (IssueType? type) {
              setState(() {
                selectedType = type;
                // Qui vanno fatte le chiamate per i filtri che cambiano le issue mostrate
              });
            },
          ),
          DropdownMenu<IssueState>(
            dropdownMenuEntries: IssueState.entries,
            label: const Text('Stato'),
            initialSelection: null,
              onSelected: (IssueState? state) {
              setState(() {
                selectedState = state;
                // Qui vanno fatte le chiamate per i filtri che cambiano le issue mostrate
              });
            },
          )
        ]
      )
    );
  }
}
