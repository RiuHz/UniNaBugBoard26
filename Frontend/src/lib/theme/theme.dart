import 'package:flutter/material.dart';

extension CustomThemeData on ThemeData {
  Color get red => Color.fromARGB(255, 255, 82, 82);
  Color get yellow => Color.fromARGB(255, 255, 235, 59);
  Color get green => Color.fromARGB(255, 76, 175, 80);
}

ThemeData lightMode = ThemeData(
  brightness: Brightness.light,
  colorScheme: ColorScheme.light(
    surface: Colors.grey.shade300,
    primary: Colors.grey.shade200,
    secondary: Colors.grey.shade100
  )
);

ThemeData darkMode = ThemeData(
  brightness: Brightness.dark,
  colorScheme: ColorScheme.dark(
    surface: Colors.grey.shade900,
    primary: Colors.grey.shade800,
    secondary: Colors.grey.shade700
  )
);
