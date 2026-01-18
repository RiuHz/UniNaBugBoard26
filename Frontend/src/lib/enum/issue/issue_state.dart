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
      case 'TODO':
        return IssueState.todo;
      case 'IN_PROGRESS':
        return IssueState.inProgress;
      case 'RESOLVED':
        return IssueState.resolved;
      default:
        throw ArgumentError('Unknown progress: $level');
    }
  }

  static final List<DropdownMenuEntry<IssueState>> entries = UnmodifiableListView<DropdownMenuEntry<IssueState>>(
    values.map<DropdownMenuEntry<IssueState>>(
      (IssueState state) => DropdownMenuEntry<IssueState>(
        value: state,
        label: state.progress
      ) 
    )
  );
}
