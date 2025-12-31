import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:form_builder_image_picker/form_builder_image_picker.dart';
import 'package:http/http.dart';
import 'package:provider/provider.dart';
import 'package:test_app/components/buttons/rounded%20loading%20button/rounded_loading_button.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';
import 'package:test_app/enum/issue/issue_type.dart';
import 'package:test_app/functions/issue/issue.dart';
import 'package:test_app/functions/open%20pop-up/pop_up.dart';
import 'package:test_app/main.dart';

class AddIssueForm extends StatefulWidget {
  const AddIssueForm({super.key});

  @override
  State<StatefulWidget> createState() => AddIssueFormState();
}

class AddIssueFormState extends State<AddIssueForm> {

  final GlobalKey<FormState> formKey = GlobalKey<FormState>();

  late String title;
  late String description;
  IssuePriority priority = IssuePriority.low;
  IssueType type = IssueType.bug;
  MultipartFile? file;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsetsGeometry.symmetric(vertical: 20, horizontal: 50),
      child: Form(
        key: formKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Titolo',
                setData: setTitle
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(
                label: 'Descrizione',
                setData: setDescription
              ),
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: DropdownMenu<IssuePriority>(
                dropdownMenuEntries: IssuePriority.entries.where((priority) => priority.value != IssuePriority.all).toList(),
                label: const Text('Priorità'),
                initialSelection: priority,
                requestFocusOnTap: false,
                  onSelected: (IssuePriority? selectedPriority) {
                  setState(() {
                    priority = selectedPriority as IssuePriority;
                  });
                },
              ), 
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: DropdownMenu<IssueType>(
                dropdownMenuEntries: IssueType.entries.where((priority) => priority.value != IssueType.all).toList(),
                label: const Text('Tipo'),
                initialSelection: type,
                requestFocusOnTap: false,
                  onSelected: (IssueType? selectedType) {
                  setState(() {
                    type = selectedType as IssueType;
                  });
                },
              ), 
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: FormBuilderImagePicker(
                name: 'Immagine',
                maxImages: 1,
                availableImageSources: const [ImageSourceOption.gallery],
                decoration: const InputDecoration(labelText: 'Seleziona immagine'),
                onSaved: (value) async {
                  if (value == null || value.isEmpty) return setState(() {file = null;});

                  setState(() {
                    file = value.first;
                  });
                },
              )
            ),
            Padding(
              padding: const EdgeInsetsGeometry.symmetric(vertical: 10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  RoundedLoadingButton(
                    text: 'Crea Issue',
                    onPressedFunction: createIssue,
                  )
                ]
              )
            )
          ],
        )
      ),
    );
  }

  Future<void> createIssue() async {
    if (!formKey.currentState!.validate()) return;

    bool issueCreated = await postIssue(Provider.of<UniNaBugBoard26State>(context).user, getFormData());

    if (!mounted) return;
      
    openPopUp(context, issueCreated, 'Issue segnalata!', 'La segnalazione è andata a buon fine.');
  }

  String getFormData() {
    return jsonEncode({
      'userid': null,
      'titolo': title,
      'descrizione': description,
      'priorita': priority.level,
      'stato': 'ToDo',
      'tipo': type.name,
      'allegato':  file
    });
  }

  void setTitle(String value) {
    setState(() {title = value;});
  }

  void setDescription(String value) {
    setState(() {description = value;});
  }
}
