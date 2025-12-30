import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:test_app/classes/issue%20fetch%20request/issue_fetch_request.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:http/http.dart' as http;

List<Issue> parseIssues(String responseBody) {
  final parsed = (jsonDecode(responseBody) as List<Object?>)
      .cast<Map<String, Object?>>();

  return parsed.map<Issue>(Issue.fromJson).toList();
}

Future<List<Issue>> fetchIssues(String userId, IssueFetchRequest request) async { // Vanno aggiunti parametri opzionali (utente, priorità, tipo, stato) se non sono presenti allora vanno considerati nulli
  final response = await http.get(
    Uri.parse('https://raw.githubusercontent.com/RiuHz/UniNaBugBoard26/refs/heads/main/Frontend/src/lib/pages/home/data.json'),   // Cambiare poi url backend
  );

  if (response.statusCode == 200) {
    return compute(parseIssues, response.body);
  } else {
    throw Exception('Failed to load issues from server...');
  }
}
