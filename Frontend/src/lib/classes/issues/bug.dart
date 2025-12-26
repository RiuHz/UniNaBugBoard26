import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';

class Bug extends Issue {
  Bug() : super(
    icon: const Icon(Icons.pest_control) 
  );

  @override
  bool isEditable() {
    return true; // Fare la logica (dovrebbe prendere un context, probabilmente quello UniNaBugBoard26 perché l'utente è conservato lì?)
  }
}
