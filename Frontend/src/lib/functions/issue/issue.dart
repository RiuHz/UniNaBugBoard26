import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:test_app/classes/issue%20fetch%20request/issue_fetch_request.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:http/http.dart' as http;
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/functions/api.dart';

List<Issue> parseIssues(String responseBody) {
  final parsed = (jsonDecode(responseBody) as List<Object?>)
      .cast<Map<String, Object?>>();

  return parsed.map<Issue>(Issue.fromJson).toList();
}

String addQueryParameters(String url, IssueFetchRequest issue, {String userId = ''}) {
  List<String> queryParameters = [];

  if (userId.isNotEmpty) {
    queryParameters.add('utente=$userId');
  }

  if (issue.getType().isNotEmpty) {
    queryParameters.add('tipo=${issue.getType()}');
  }

  if (issue.getState().isNotEmpty) {
    queryParameters.add('stato=${issue.getState()}');
  }

  if (queryParameters.isNotEmpty) {
    return '$url?${queryParameters.join('&')}';
  }

  return url;
}

Future<List<Issue>> getIssues(LoggedUser user, IssueFetchRequest issue) async {
   final response = await http.get(
      Uri.parse(addQueryParameters(apiURL, issue)),
      headers: {'Authorization': user.token}
  );

  if (response.statusCode == 200) {
    return compute(parseIssues, response.body);
  } else {
    throw Exception('Failed to load issues from server...');
  }
}

Future<List<Issue>> getUserIssues(LoggedUser user, IssueFetchRequest issue) async {
   final response = await http.get(
      Uri.parse(addQueryParameters(apiURL, issue, userId: user.id)),
      headers: {'Authorization': user.token}
  );

  if (response.statusCode == 200) {
    return compute(parseIssues, response.body);
  } else {
    throw Exception('Failed to load issues from server...');
  }
}

Future<bool> patchIssue(LoggedUser user, int id) async {
  final response = await http.patch(
    Uri.parse('$apiURL/$id'),
    headers: {'Authorization': user.token}
  );

  return response.statusCode == 200;
}

Future<bool> postIssue(LoggedUser user, String json) async {
    final response = await http.post(
    Uri.parse(apiURL),
    body: json,
    headers: {'Authorization': user.token}
  );

  return response.statusCode == 200;
}
