import 'package:flutter/material.dart';

class RoundedTextFormField extends StatefulWidget {
  final String label;
  final bool obscureText;
  final void Function(String) setData;

  const RoundedTextFormField({
    super.key,
    required this.label,
    required this.setData,
    this.obscureText = false
  });

  @override
  State<StatefulWidget> createState() => RoundedTextFormFieldState();
}
 
class RoundedTextFormFieldState extends State<RoundedTextFormField> {


  @override
  Widget build(BuildContext context) {
    return TextFormField(
      decoration: InputDecoration(
        labelText: widget.label,
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
      obscureText: widget.obscureText,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Questo campo non può essere vuoto';
        }

        return null;
      },
      onChanged: (value) {widget.setData(value);},
    );
  }
}
