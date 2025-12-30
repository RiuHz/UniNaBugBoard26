import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:test_app/components/rounded%20button/rounded_button.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';

class LogInForm extends StatefulWidget {
  const LogInForm({super.key});

  @override
  State<StatefulWidget> createState() => LogInFormState();
}

class LogInFormState extends State<LogInForm> {

  final GlobalKey<FormState> formKey = GlobalKey<FormState>();

  late String email;
  late String password;

  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300.0,
      child: Form(
        key: formKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Email'
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Password',
                obscureText: true
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: isLoading
                ? const CircularProgressIndicator()
                : RoundedButton(
                    text: 'Effettua l\'Accesso',
                    onPressedFunction: logInUser,
                  )
            )
          ],
        )
      ),
    );
  }

  void logInUser() {
    if (!formKey.currentState!.validate()) {
      return;
    }

    ScaffoldMessenger.of(context).showSnackBar(
      const SnackBar(
        content: Text('Funziona...?') // Qua va fatta la call al server e attesa la risposta, rendere il bottone che carica
      )
    );

    // Devo prendere i valori di email, nome, cognome e password al click del bottone

    // Se va bene imposto selectedPage a HomePage()
    setState(() {isLoading = true;});
  }
}
