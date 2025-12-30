import 'package:flutter/material.dart';
import 'package:test_app/components/buttons/rounded%20button/rounded_button.dart';

class RoundedLoadingButton extends StatefulWidget {
  final String text;
  final Future<void> Function() onPressedFunction;

  const RoundedLoadingButton({
    super.key,
    required this.text,
    required this.onPressedFunction
  });

  @override
  State<StatefulWidget> createState() => RoundedLoadingButtonState();
}

class RoundedLoadingButtonState extends State<RoundedLoadingButton> {

  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return isLoading
      ? const CircularProgressIndicator()
      : RoundedButton(
          text: widget.text,
          onPressedFunction: buttonPressed
        );
  }

  Future<void> buttonPressed() async {
    setState(() {
      isLoading = true;
    });

    await widget.onPressedFunction();

    setState(() {
      isLoading = false;
    });
  }
}