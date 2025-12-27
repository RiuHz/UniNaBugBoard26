import 'package:flutter/material.dart';

class TextInputField extends StatefulWidget {
  final String title;

  const TextInputField({
    super.key,
    required this.title
  });

  @override
  State<TextInputField> createState() => TextInputFieldState();
}

class TextInputFieldState extends State<TextInputField> {

  @override
  Widget build(BuildContext context) {
    return TextField(
      maxLines: null,
      decoration: InputDecoration(
        labelText: widget.title,
        floatingLabelStyle: TextStyle(
          color: Theme.of(context).colorScheme.inverseSurface
        ),
        border: OutlineInputBorder(),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(
            color: Theme.of(context).colorScheme.inverseSurface
          )
        )
      ),
      // onSubmitted: Qui va preso il valore del field
    );
  }
}
