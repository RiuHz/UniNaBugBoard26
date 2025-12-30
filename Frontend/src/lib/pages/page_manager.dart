import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/pages/home/home_page.dart';
import 'package:test_app/pages/login/login_page.dart';
import 'package:test_app/pages/my%20bugs/my_bugs_page.dart';
import 'package:test_app/pages/add%20issue/add_issue_page.dart';
import 'package:test_app/pages/signup%20user/signup_page.dart';

class PageManager extends StatelessWidget {  
  const PageManager({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => PageManagerState(),
      child: Consumer<PageManagerState> (
        builder: (context, state, child) {
          return state.selectedPage;
        }
      )
    );
  }
}

class PageManagerState extends ChangeNotifier {
  Widget selectedPage = const LogInUserPage();
  int selectedIndex = 0;

  void switchPage(int index) {
    selectedIndex = index;

    switch (index) {
      case 0:
        selectedPage = const HomePage();
        break;
      case 1:
        selectedPage = const MyBugsPage();
        break;
      case 2:
        selectedPage = const AddIssuePage();
        break;
      case 3:
        selectedPage = const SignUpUserPage();
        break;
      default:
        throw UnimplementedError('No widget page for $index');
    }

    notifyListeners();
  }
}
