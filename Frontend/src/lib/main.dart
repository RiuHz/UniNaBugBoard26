import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/user/user.dart';
import 'package:test_app/theme%20button/theme_button.dart';
import 'package:test_app/theme/theme.dart';
import 'package:test_app/pages/page_manager.dart';

void main() {
  runApp(const UniNaBugBoard26());
}

class UniNaBugBoard26 extends StatelessWidget {
  const UniNaBugBoard26({super.key});

  final String title = 'UniNaBugBoard26';

  @override
  Widget build(BuildContext context) {
      return ChangeNotifierProvider(
        create: (context) => UniNaBugBoard26State(),
        child: Consumer<UniNaBugBoard26State> (
          builder: (context, state, child) {
            return MaterialApp(
              title: title,
              themeMode: state.themeMode,
              theme: lightMode,
              darkTheme: darkMode,
              home: Scaffold(
                appBar: AppBar(
                  title: Text(title),
                  centerTitle: true,
                ),
                body: const PageManager(),
                floatingActionButton: const ThemeButton(),
              )
            );
          }
        ) 
      );
  }
}

class UniNaBugBoard26State extends ChangeNotifier {
  bool lightTheme = ThemeMode.system == ThemeMode.light;

  User user = User(
    id: '1',
    name: 'Pino',
    surname: 'Pino',
    role: 'Admin'
  ); // Qui l'utente va preso dal login

  void switchTheme() {
    lightTheme = !lightTheme;

    notifyListeners();
  }

  ThemeMode get themeMode => lightTheme ? ThemeMode.light : ThemeMode.dark;
}
