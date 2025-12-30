import 'package:flutter/material.dart';
import 'package:test_app/components/form/login/login_form.dart';

class LogInUserPage extends StatelessWidget {
  const LogInUserPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            LogInForm()
          ],
        )
      ]
    );
  }
}
