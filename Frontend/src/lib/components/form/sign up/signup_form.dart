import 'package:flutter/material.dart';
import 'package:test_app/components/rounded%20button/rounded_button.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';
import 'package:test_app/enum/user/user_role.dart';

class SignUpForm extends StatefulWidget {
  const SignUpForm({super.key});

  @override
  State<StatefulWidget> createState() => SignUpFormState();
}

class SignUpFormState extends State<SignUpForm> {

  final formKey = GlobalKey<FormState>();

  late String email;
  late String name;
  late String surname;
  late String password;
  late UserRole? role;

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
                label: 'Nome'
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Cognome'
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
              child: DropdownMenu<UserRole>(
                dropdownMenuEntries: UserRole.entries,
                label: const Text('Stato'),
                initialSelection: UserRole.developer,
                requestFocusOnTap: false,
                  onSelected: (UserRole? selectedRole) {
                  setState(() {
                    role = selectedRole;
                  });
                },
            ),
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: isLoading
                ? const CircularProgressIndicator()
                : RoundedButton(
                    text: 'Registra l\'Utente',
                    onPressedFunction: registerUser,
                  )
            )
          ],
        )
      ),
    );
  }

  void registerUser() {
    if (!formKey.currentState!.validate()) {
      return;
    }

    ScaffoldMessenger.of(context).showSnackBar(
      const SnackBar(
        content: Text('Funziona...?') // Qua va fatta la call al server e attesa la risposta, rendere il bottone che carica
      )
    );

    // Devo prendere i valori di email, nome, cognome e password al click del bottone

    setState(() {isLoading = true;});
  }
}
