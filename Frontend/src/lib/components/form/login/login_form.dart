import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';
import 'package:test_app/components/buttons/rounded%20loading%20button/rounded_loading_button.dart';
import 'package:test_app/functions/auth/login/login.dart';
import 'package:test_app/functions/open%20pop-up/pop_up.dart';
import 'package:test_app/main.dart';
import 'package:test_app/pages/page_manager.dart';

class LogInForm extends StatefulWidget {
  const LogInForm({super.key});

  @override
  State<StatefulWidget> createState() => LogInFormState();
}

class LogInFormState extends State<LogInForm> {

  final GlobalKey<FormState> formKey = GlobalKey<FormState>();

  late String email;
  late String password;

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
                label: 'Email',
                setData: setEmail
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Password',
                setData: setPassword,
                obscureText: true
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedLoadingButton(
                text: 'Effettua l\'Accesso',
                onPressedFunction: logIn,
              )
            )
          ],
        )
      ),
    );
  }

  Future<void> logIn() async {
    if (!formKey.currentState!.validate()) return;

    LoggedUser? userLoggedIn = await logInUser(email, password);

    if (!mounted) return;

    if (userLoggedIn == null) {
      openPopUp(context, false, '', '');
    } else {
      openUserHomePage(userLoggedIn);
    }
  }

  void openUserHomePage(LoggedUser user) {
    Provider.of<UniNaBugBoard26State>(context, listen: false).user = user;
    Provider.of<PageManagerState>(context, listen: false).switchPage(0);
  }

  void setEmail(String value) {
    setState(() {email = value;});
  }

  void setPassword(String value) {
    setState(() {password = value;});
  }
}
