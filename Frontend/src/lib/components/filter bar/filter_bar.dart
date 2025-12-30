import 'package:flutter/material.dart';
import 'package:test_app/classes/issue%20fetch%20request/issue_fetch_request.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/enum/issue/issue_state.dart';
import 'package:test_app/enum/issue/issue_type.dart';

class FilterBar extends StatefulWidget {
  final void Function(IssueFetchRequest) updateIssueList; 
  
  const FilterBar({
    super.key,
    required this.updateIssueList
  });

  @override
  State<FilterBar> createState() => FilterBarState();
}

class FilterBarState extends State<FilterBar> {
  IssuePriority? selectedPriority = IssuePriority.all;
  IssueType? selectedType = IssueType.all;
  IssueState? selectedState = IssueState.all;

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
            initialSelection: selectedPriority,
            requestFocusOnTap: false,
            onSelected: (IssuePriority? priority) {
              setState(() {
                selectedPriority = priority;
                filterIssues();
              });
            },
          ),
          DropdownMenu<IssueType>(
            dropdownMenuEntries: IssueType.entries,
            label: const Text('Tipo'),
            initialSelection: selectedType,
            requestFocusOnTap: false,
              onSelected: (IssueType? type) {
              setState(() {
                selectedType = type;
                filterIssues();
              });
            },
          ),
          DropdownMenu<IssueState>(
            dropdownMenuEntries: IssueState.entries,
            label: const Text('Stato'),
            initialSelection: selectedState,
            requestFocusOnTap: false,
              onSelected: (IssueState? state) {
              setState(() {
                selectedState = state;
                filterIssues();
              });
            },
          )
        ]
      )
    );
  }

  void filterIssues() {
    widget.updateIssueList(IssueFetchRequest(
      priority: selectedPriority as IssuePriority,
      type: selectedType as IssueType, 
      state: selectedState as IssueState
    ));
  }
}
