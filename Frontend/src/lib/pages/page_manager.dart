import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/navbar/navbar.dart';
import 'package:test_app/pages/home/home.dart';
import 'package:test_app/pages/my%20bugs/my_bugs.dart';
import 'package:test_app/pages/add%20issue/add_issue.dart';
import 'package:test_app/pages/register%20user/register_user.dart';

class PageManager extends StatelessWidget {  
  const PageManager({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => PageManagerState(),
      child: Consumer<PageManagerState> (
        builder: (context, state, child) {
          return Column(
            children: [
              const Navbar(),
              Expanded(
                child: state.selectedPage
              )
            ]
          );
        }
      )
    );
  }
}

class PageManagerState extends ChangeNotifier {
  Widget selectedPage = const HomePage();
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
        selectedPage = const RegisterUserPage();
        break;
      default:
        throw UnimplementedError('No widget page for $index');
    }

    notifyListeners();
  }
}
