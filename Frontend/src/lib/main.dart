import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/button/theme_button.dart';
import 'package:test_app/theme/theme.dart';
import 'package:test_app/pages/page_manager.dart';

void main() {
  runApp(UniNaBugBoard26());
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
                body: PageManager(),
                floatingActionButton: ThemeButton(),
              )
            );
          }
        ) 
      );
  }
}

class UniNaBugBoard26State extends ChangeNotifier {
  bool lightTheme = ThemeMode.system == ThemeMode.light;

  void switchTheme() {
    lightTheme = !lightTheme;

    notifyListeners();
  }

  ThemeMode get themeMode => lightTheme ? ThemeMode.light : ThemeMode.dark;
}
