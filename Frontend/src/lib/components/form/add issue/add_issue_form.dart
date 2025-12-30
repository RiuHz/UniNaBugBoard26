import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:form_builder_image_picker/form_builder_image_picker.dart';
import 'package:test_app/components/rounded%20button/rounded_button.dart';
import 'package:test_app/enum/issue/issue_priority.dart';
import 'package:test_app/components/rounded%20text%20form%20field/rounded_text_form_field.dart';

class AddIssueForm extends StatefulWidget {
  const AddIssueForm({super.key});

  @override
  State<StatefulWidget> createState() => AddIssueFormState();
}

class AddIssueFormState extends State<AddIssueForm> {

  final GlobalKey<FormState> formKey = GlobalKey<FormState>();

  late String title;
  late String description;
  late IssuePriority? priority;
  late Uint8List imageBytes;
  String state = 'ToDO';

  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsetsGeometry.symmetric(vertical: 20, horizontal: 50),
      child: Form(
        key: formKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: EdgeInsets.symmetric(vertical: 10),
              child: RoundedTextFormField(label: 'Titolo')
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: RoundedTextFormField(label: 'Descrizione'),
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: FormBuilderImagePicker(
                name: 'Immagine',
                maxImages: 1,
                availableImageSources: [ImageSourceOption.gallery],
                decoration: InputDecoration(labelText: 'Seleziona immagine'),
                onSaved: (newValue) async {
                  if (newValue == null || newValue.isEmpty) {
                    return;
                  }

                  XFile selectedImage = newValue.first;
                  Uint8List bytes = await selectedImage.readAsBytes();

                  setState(() {
                    imageBytes = bytes;
                  });
                },
              )
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: DropdownMenu<IssuePriority>(
                dropdownMenuEntries: IssuePriority.entries.where((priority) => priority.value != IssuePriority.all).toList(),
                label: const Text('Priorità'),
                initialSelection: IssuePriority.low,
                requestFocusOnTap: false,
                  onSelected: (IssuePriority? selectedPriority) {
                  setState(() {
                    priority = selectedPriority;
                  });
                },
              ), 
            ),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(vertical: 10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  if (isLoading)
                    const CircularProgressIndicator()
                  else 
                    RoundedButton(
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

  void createIssue() {
    if (!formKey.currentState!.validate()) {
      return;
    }

    ScaffoldMessenger.of(context).showSnackBar(
      const SnackBar(
        content: Text('Funziona...?') // Qua va fatta la call al server e attesa la risposta, rendere il bottone che carica
      )
    );

    // Devo prendere i valori di email, nome, cognome e password al click del bottone

    setState(() {isLoading = true;});
  }
}
