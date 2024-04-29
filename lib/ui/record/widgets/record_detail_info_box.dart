import 'package:beyond_vision/core/constants.dart';
import 'package:flutter/material.dart';
import 'package:beyond_vision/model/record_model.dart';

import 'package:beyond_vision/ui/record/widgets/record_detail_dialog.dart';

class DetailBox extends StatelessWidget {
  final Record record;
  const DetailBox({super.key, required this.record});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 5),
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: const Color(boxColor),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(30),
          ),
        ),
        onPressed: () {
          showDialog(
              context: context,
              builder: (BuildContext context) => RecordDetailDialog(
                    record: record,
                  ));
        },
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 20.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(record.exerciseName!,
                  style: const TextStyle(
                      color: Colors.white,
                      fontSize: 32,
                      fontWeight: FontWeight.bold)),
              Text('${(record.exerciseTime! / 60).toStringAsFixed(1)}분',
                  style: const TextStyle(color: Colors.white, fontSize: 28)),
            ],
          ),
        ),
      ),
    );
  }
}
