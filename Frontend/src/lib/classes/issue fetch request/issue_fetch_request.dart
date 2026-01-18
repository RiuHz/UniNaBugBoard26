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

  String getPriority() => priority != IssuePriority.all ? priority.level.toUpperCase() : '';
  String getType() => type != IssueType.all ? type.name.toUpperCase() : '';
  String getState() => state != IssueState.all ? state.progress.toUpperCase() : '';
}
