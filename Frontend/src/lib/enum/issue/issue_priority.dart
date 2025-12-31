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
      case 'BASSA':
        return IssuePriority.low;
      case 'MEDIA':
        return IssuePriority.medium;
      case 'ALTA':
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
