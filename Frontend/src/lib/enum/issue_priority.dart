import 'dart:collection';
import 'package:flutter/material.dart';

enum IssuePriority {
  all('Tutte'),
  low('Low'),
  medium('Medium'),
  high('High');

  const IssuePriority(this.level);

  final String level;

  static final List<DropdownMenuEntry<IssuePriority>> entries = UnmodifiableListView<DropdownMenuEntry<IssuePriority>>(
    values.map<DropdownMenuEntry<IssuePriority>>(
      (IssuePriority priority) =>  DropdownMenuEntry<IssuePriority>(
        value: priority,
        label: priority.level
      ) 
    )
  );
}
