import 'package:flutter/material.dart';
import 'package:test_app/functions/api.dart';
import 'package:test_app/classes/user/logged_user.dart';

Widget? getImageFromURL(LoggedUser user, String imageURL) {
  return Image.network(
    '$apiURL/images/$imageURL',
    headers: {'Authorization': user.token},
  );
}
