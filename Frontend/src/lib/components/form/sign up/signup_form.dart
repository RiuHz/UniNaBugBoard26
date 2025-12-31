import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/sign%20up%20request/sign_up_request.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';
import 'package:test_app/components/buttons/rounded%20loading%20button/rounded_loading_button.dart';
import 'package:test_app/enum/user/user_role.dart';
import 'package:test_app/functions/signup/signup.dart';
import 'package:test_app/functions/open%20pop-up/pop_up.dart';
import 'package:test_app/main.dart';

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
  UserRole role = UserRole.developer;

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
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Email',
                setData: setEmail
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Nome',
                setData: setName
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Cognome',
                setData: setSurname
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Password',
                setData: setPassword,
                obscureText: true
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: DropdownMenu<UserRole>(
                dropdownMenuEntries: UserRole.entries,
                label: const Text('Stato'),
                initialSelection: role,
                requestFocusOnTap: false,
                  onSelected: (UserRole? selectedRole) {
                  setState(() {
                    role = selectedRole as UserRole;
                  });
                },
            ),
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child:  RoundedLoadingButton(
                text: 'Registra l\'Utente',
                onPressedFunction: signUp,
              )
            )
          ],
        )
      ),
    );
  }

  Future<void> signUp() async {
    if (!formKey.currentState!.validate()) return;

    bool userSignedUp = await postUser(Provider.of<UniNaBugBoard26State>(context).user, getFormData());

    if (!mounted) return;
     
    openPopUp(context, userSignedUp, 'Utente registrato!', 'La registrazione è andata a buon fine.');
  }

  SignUpRequest getFormData() {
    return SignUpRequest(
      email,
      password,
      name,
      surname,
      role
    );
  }

  void setEmail(String value) {
    setState(() {email = value;});
  }

  void setName(String value) {
    setState(() {name = value;});
  }

  void setSurname(String value) {
    setState(() {surname = value;});
  }

  void setPassword(String value) {
    setState(() {password = value;});
  }
}
