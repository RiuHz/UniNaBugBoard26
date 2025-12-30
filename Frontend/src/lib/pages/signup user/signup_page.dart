import 'package:flutter/material.dart';
import 'package:test_app/components/form/sign%20up/signup_form.dart';
import 'package:test_app/components/navbar/navbar.dart';

class SignUpUserPage extends StatelessWidget {
  const SignUpUserPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Navbar(),
        Expanded(
          child: SignUpForm()
        )
      ]
    );
  }
}
