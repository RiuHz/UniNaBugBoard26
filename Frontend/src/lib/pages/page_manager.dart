import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/navbar/navbar.dart';

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
  Widget selectedPage = Placeholder(); // Dovrei metterci la pagina di default
  int selectedIndex = 0;

  void switchPage(int index) {
    selectedIndex = index;

    switch (index) {
      case 0:
        selectedPage = const Placeholder();
        break;
      case 1:
        selectedPage = const Placeholder();
        break;
      case 2:
        selectedPage = const Placeholder();
        break;
      default:
        throw UnimplementedError('No widget for $index');
    }

    notifyListeners();
  }
}
