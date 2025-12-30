import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/main.dart';

class ThemeButton extends StatelessWidget {
  const ThemeButton({super.key});

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: Icon(Provider.of<UniNaBugBoard26State>(context).lightTheme ? Icons.light_mode : Icons.dark_mode),
      onPressed: () {
        Provider.of<UniNaBugBoard26State>(context, listen: false).switchTheme();
      }
    );
  }
}