import 'dart:collection';
import 'package:flutter/material.dart';

enum IssueState {
  todo('ToDo'),
  inProgress('In Progress'),
  resolved('Resolved');

  const IssueState(this.progress);

  final String progress;

  static final List<DropdownMenuEntry<IssueState>> entries = UnmodifiableListView<DropdownMenuEntry<IssueState>>(
    values.map<DropdownMenuEntry<IssueState>>(
      (IssueState state) =>  DropdownMenuEntry<IssueState>(
        value: state,
        label: state.progress
      ) 
    )
  );
}
