import 'package:flutter/material.dart';

class RoundedButton extends StatelessWidget {

  final String text;
  final void Function() onPressedFunction;
  final double roundingValue;
  
  const RoundedButton({
    super.key,
    required this.text,
    this.onPressedFunction = RoundedButton.emptyFunction,
    this.roundingValue = 25
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        shadowColor: Theme.of(context).colorScheme.inverseSurface,
        shape: RoundedRectangleBorder(
          side: BorderSide(
            color: Theme.of(context).colorScheme.inverseSurface,
          ),
          borderRadius: BorderRadiusGeometry.circular(roundingValue)
        )
      ),
      onPressed: onPressedFunction,
      child: Text(
        text,
        style: TextStyle(
          color: Theme.of(context).colorScheme.inverseSurface,
          fontSize: 16
        )
      )
    );
  }

  static void emptyFunction() {}
}
