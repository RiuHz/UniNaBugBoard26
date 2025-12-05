import 'package:flutter/material.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:provider/provider.dart';
import 'package:test_app/navbar/navbar.dart';

// Aggiunta libreria per la decodifica del token JWT
import 'package:jwt_decoder/jwt_decoder.dart';


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
              /* INIZIO SEZIONE DI PROVA PER DECIFARE UN TOKEN JWT*/ 
              Row(
              children: [
                Expanded(
                  child: Center(
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.grey,
                        foregroundColor: Colors.white,
                      ),
                      onPressed: () {

                        /*
                        Token utilizzato per il test:
                        {
                          "ID": "23145",
                          "Utente": "Franco Ricciardi",
                          "Titolo": "Bug rilevato sul progetto n° 243",
                          "Descrizione": "Una volta eseguito l' accesso, l' app si chiude",
                          "Priorità": "Alta",
                          "Stato": "In progress",
                          "Tipo": "Bug"
                        }
                        */ 

                        // Token codificato (Algorithm: HS256 , Key: Centosessantasette)

                        String token = "eyJhbGciOiJIUzI1NiJ9.eyJUaXBvIjoiQnVnIiwiVGl0b2xvIjoiQnVnIHJpbGV2YXRvIHN1bCBwcm9nZXR0byBuwrAgMjQzIiwiVXRlbnRlIjoiRnJhbmNvIFJpY2NpYXJkaSIsIkRlc2NyaXppb25lIjoiVW5hIHZvbHRhIGVzZWd1aXRvIGwnIGFjY2Vzc28sIGwnIGFwcCBzaSBjaGl1ZGUiLCJQcmlvcml0w6AiOiJBbHRhIiwiSUQiOiIyMzE0NSIsIlN0YXRvIjoiSW4gcHJvZ3Jlc3MifQ.5pE4piH8iq6KKrsC315Cu0VVqEh3ICiyPyZ2RUUjw9s";

                        Map<String, dynamic> decodedToken = JwtDecoder.decode(token);

                        // Stampa dell' intero token decodificato e di alcuni campi specifici
                        
                        print("JWT Token decodificato: $decodedToken");

                        print("ID: ${decodedToken['ID']}");

                        print("Priorità: ${decodedToken['Priorità']}");

                      },
                      child: Text("Decodifica JWT Token ORA (Vedi risultato nella debug console)"),
                    ),
                  ),
                ),
              ],
            ),
            /* FINE SEZIONE DI PROVA PER DECIFARE UN TOKEN JWT*/ 
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
  Widget selectedPage = const Placeholder(); // Dovrei metterci la pagina di default
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
      case 3:
        selectedPage = const Placeholder();
        break;
      default:
        throw UnimplementedError('No widget for $index');
    }

    notifyListeners();
  }
}
