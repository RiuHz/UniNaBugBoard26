import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:test_app/classes/issues/issue.dart';
import 'package:test_app/components/buttons/rounded%20loading%20button/rounded_loading_button.dart';
import 'package:test_app/functions/images/images.dart';
import 'package:test_app/functions/issue/issue.dart';
import 'package:test_app/functions/open%20pop-up/pop_up.dart';
import 'package:test_app/main.dart';

class BugDetailPage extends StatefulWidget {
  final Issue issue;

  const BugDetailPage({
    super.key,
    required this.issue
  });

  @override
  State<StatefulWidget> createState() => BugDetailPageState();

}

class BugDetailPageState extends State<BugDetailPage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.issue.title)
      ),
      body: Center(
        child: Hero(
          tag: 'Issue Detail',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      widget.issue.description,
                      style: const TextStyle(
                        fontSize: 16
                      )  
                    )
                  ],
                )
              ),
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  'Stato: ${widget.issue.state.progress}',
                  style: const TextStyle(
                    fontSize: 22
                  )    
                ) 
              ),
              Padding(
                padding:const  EdgeInsets.symmetric(vertical: 10.0, horizontal: 25.0),
                child: Text(
                  'Priorità: ${widget.issue.priority.level}',
                  style: const TextStyle(
                    fontSize: 22
                  )  
                )
              ),
              Padding(
                padding: const EdgeInsetsGeometry.symmetric(vertical: 10.0, horizontal: 25.0),
                child: widget.issue.image.isEmpty
                  ? const Text('Nessun immagine prevista')
                  : SizedBox(
                    width: 300,
                    height: 300,
                    child: getImageFromURL(Provider.of<UniNaBugBoard26State>(context).user, widget.issue.image)
                  )
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                    RoundedLoadingButton(
                      text: 'Segna come Resolved',
                      onPressedFunction: markIssueAsResolved
                    )
                ],
              )
            ],
          ),
        )
      )
    );
  }

  Future<void> markIssueAsResolved() async {

    bool issuePatched = await patchIssue(Provider.of<UniNaBugBoard26State>(context).user, widget.issue.id);
    
    if (mounted) {
      openPopUp(context, issuePatched, 'Issue risolta!', 'La issue adesso è segnalta come risolta.');
    }
  }
}
