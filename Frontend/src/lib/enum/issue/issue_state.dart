import 'dart:collection';
import 'package:flutter/material.dart';

enum IssueState {
  all('Tutti'),
  todo('To Do'),
  inProgress('In Progress'),
  resolved('Resolved');

  const IssueState(this.progress);

  final String progress;

  static IssueState fromString(String level) {
    switch (level) {
      case 'To Do':
        return IssueState.todo;
      case 'In Progress':
        return IssueState.inProgress;
      case 'Resolved':
        return IssueState.resolved;
      default:
        throw ArgumentError('Unknown progress: $level');
    }
  }

  static final List<DropdownMenuEntry<IssueState>> entries = UnmodifiableListView<DropdownMenuEntry<IssueState>>(
    values.map<DropdownMenuEntry<IssueState>>(
      (IssueState state) =>  DropdownMenuEntry<IssueState>(
        value: state,
        label: state.progress
      ) 
    )
  );
}
