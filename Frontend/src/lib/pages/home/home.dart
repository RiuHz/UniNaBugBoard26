import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:test_app/classes/issue/issue.dart';


class HomePage extends StatelessWidget {
  final List<Issue> issues = get; // Questa dovrà diventare una richiesta HTTPS ai servizi amazon aws 

  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text('Filtri'),
        ListView.builder(
          itemCount: issues.length,
          prototypeItem: issues.first,
          itemBuilder: (context, index) {
            return issues[index];
          },
        )
      ],
    );
  }
}

List<Issue> parseIssues(String responseBody) {
  final parsed = (jsonDecode(responseBody) as List<Object?>)
      .cast<Map<String, Object?>>();

  return parsed.map<Issue>(Issue.fromJson).toList();
}

Future<List<Issue>> fetchIssues(http.Client client) async {
/* COSI SI FA LA RICHIESTA HTTP
  final response = await client.get(
    Uri.parse('https://jsonplaceholder.typicode.com/photos'),
  );

  return compute(parseIssues, response.body);  
*/ 

  // Questa invece è solo una lettura da disco in fase di sviluppo
  



}
