import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/pages/page_manager.dart';

class Navbar extends StatelessWidget {
  const Navbar({super.key});

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: NavigationBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        labelBehavior: NavigationDestinationLabelBehavior.onlyShowSelected,
        destinations: [
          const NavbarDestination(
            label: 'Aggiungi un Bug',
            iconData: Icons.add
          ),
          const NavbarDestination(
            label: 'Home',
            iconData: Icons.home
          ),
          const NavbarDestination(
            label: 'Miei Bug',
            iconData: Icons.bug_report
          )
        ],
        selectedIndex: Provider.of<PageManagerState>(context).selectedIndex,
        onDestinationSelected: (int index) {
          Provider.of<PageManagerState>(context, listen: false).switchPage(index);
        }
      )
    );
  }
}

class NavbarDestination extends StatelessWidget {
  final String label;
  final IconData iconData;

  const NavbarDestination({
    required this.label,
    required this.iconData,
    super.key
  });

  @override
  Widget build(BuildContext context) {
    return NavigationDestination(
      icon: Icon(iconData),
      label: label,
      selectedIcon: Icon(iconData, color: Theme.of(context).colorScheme.inverseSurface),
    );
  }
}
