import 'package:flutter/material.dart';

class RoundedTextFormField extends StatelessWidget {
  final String label;
  final bool obscureText;

  const RoundedTextFormField({
    super.key,
    required this.label,
    this.obscureText = false
  });

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      decoration: InputDecoration(
        labelText: label,
        floatingLabelStyle: TextStyle(
          color: Theme.of(context).colorScheme.inverseSurface
        ),
        border: OutlineInputBorder(),
          focusedBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Theme.of(context).colorScheme.inverseSurface
            ),
        )
      ),
      obscureText: obscureText,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Questo campo non può essere vuoto';
        }

        return null;
      }
    );
  }
}
