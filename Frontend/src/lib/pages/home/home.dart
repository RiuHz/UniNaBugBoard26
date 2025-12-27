import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:test_app/filter/filter_bar.dart';
import 'package:test_app/components/issue%20card/issue_card.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<StatefulWidget> createState() => HomePageState();
}

class HomePageState extends State<HomePage> {
  late Future<List<Issue>> issues = fetchIssues();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        FilterBar(),
        FutureBuilder<List<Issue>> (
          future: issues,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const CircularProgressIndicator();
            } else if (snapshot.hasError) {
              return Text('Ops... Qualcosa è andato storto');
            } else if (snapshot.hasData) {
              return Expanded(
                child: buildIssuesList(snapshot.data!)
              );
            } else {
              return const Text('Non sono presenti Issue al momento!');
            }
          }
        )
      ],
    );
  }

  Widget buildIssuesList(List<Issue> issues) => ListView.builder(
    itemCount: issues.length,
    itemBuilder: (context, index) {
      final Issue issue = issues[index];

      return IssueCard(
        issue: issue
      );
    }
  );
}

List<Issue> parseIssues(String responseBody) {
  final parsed = (jsonDecode(responseBody) as List<Object?>)
      .cast<Map<String, Object?>>();

  return parsed.map<Issue>(Issue.fromJson).toList();
}

Future<List<Issue>> fetchIssues() async {
  // Cambiare poi url ai servizi AWS
  final response = await http.get(
    Uri.parse('https://raw.githubusercontent.com/RiuHz/UniNaBugBoard26/refs/heads/main/Frontend/src/lib/pages/home/data.json'),
  );

  if (response.statusCode == 200) {
    return compute(parseIssues, response.body);
  } else {
    throw Exception('Failed to load issues from server...');
  }
}
