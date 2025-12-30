import 'dart:collection';
import 'package:flutter/material.dart';

enum IssuePriority {
  all('Tutte'),
  low('Bassa'),
  medium('Media'),
  high('Alta');

  final String level;

  const IssuePriority(this.level);

  static IssuePriority fromString(String level) {
    switch (level) {
      case 'Bassa':
        return IssuePriority.low;
      case 'Media':
        return IssuePriority.medium;
      case 'Alta':
        return IssuePriority.high;
      default:
        throw ArgumentError('Unknown priority level: $level');
    }
  }

  static final List<DropdownMenuEntry<IssuePriority>> entries = UnmodifiableListView<DropdownMenuEntry<IssuePriority>>(
    values.map<DropdownMenuEntry<IssuePriority>>(
      (IssuePriority priority) =>  DropdownMenuEntry<IssuePriority>(
        value: priority,
        label: priority.level
      ) 
    )
  );
}
