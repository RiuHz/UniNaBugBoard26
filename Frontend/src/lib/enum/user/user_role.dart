import 'dart:collection';
import 'package:flutter/material.dart';

enum UserRole {
  developer('Sviluppatore'),
  admin('Admin');

  final String type;

  const UserRole(this.type);

  static UserRole fromString(String type) {
    switch(type) {
      case 'SVILUPPATORE':
        return UserRole.developer;
      case 'ADMIN':
        return UserRole.admin;
      default:
        throw ArgumentError('Unknown role type: $type');
    }
  }
  
  static final List<DropdownMenuEntry<UserRole>> entries = UnmodifiableListView<DropdownMenuEntry<UserRole>>(
    values.map<DropdownMenuEntry<UserRole>>(
      (UserRole role) =>  DropdownMenuEntry<UserRole>(
        value: role,
        label: role.type
      ) 
    )
  );
}
