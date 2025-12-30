import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/enum/issue/issue_state.dart';
import 'package:test_app/enum/issue/issue_type.dart';

class IssueFetchRequest {
  final IssuePriority priority;
  final IssueType type;
  final IssueState state;

  const IssueFetchRequest({
    required this.priority,
    required this.type,
    required this.state
  });
}
