import 'package:test_app/classes/sign%20up%20request/sign_up_request.dart';
import 'package:http/http.dart' as http;
import 'package:test_app/classes/user/logged_user.dart';
import 'package:test_app/functions/api.dart';

Future<bool> postUser(LoggedUser user, SignUpRequest request) async {
  
  final response = await http.post(
    Uri.parse('$apiURL/sign-up'),
    body: request.getJsonData(),
    headers: {'Authorization': user.token}
  );

  return response.statusCode == 200;
}
