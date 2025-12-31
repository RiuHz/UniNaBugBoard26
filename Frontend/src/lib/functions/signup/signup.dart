import 'package:test_app/classes/sign%20up%20request/sign_up_request.dart';
import 'package:http/http.dart' as http;
import 'package:test_app/classes/user/logged_user.dart';

Future<bool> postUser(LoggedUser user, SignUpRequest request) async {
  
  final response = await http.post(
    Uri.parse('https://px7ldiamld.execute-api.eu-south-1.amazonaws.com/api/v1/issues'),
    body: request.getJsonData(),
    headers: {'CognitoToken': user.token}
  );

  return response.statusCode == 200;
}
