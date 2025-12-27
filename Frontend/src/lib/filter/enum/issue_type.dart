import 'dart:collection';

import 'package:flutter/material.dart';

enum IssueType {
  bug('Bug'),
  question('Question'),
  feature('Feature'),
  documentation('Documentation');

  const IssueType(this.name);

  final String name;

  static final List<DropdownMenuEntry<IssueType>> entries = UnmodifiableListView<DropdownMenuEntry<IssueType>>(
    values.map<DropdownMenuEntry<IssueType>>(
      (IssueType type) =>  DropdownMenuEntry<IssueType>(
        value: type,
        label: type.name
      ) 
    )
  );
}
