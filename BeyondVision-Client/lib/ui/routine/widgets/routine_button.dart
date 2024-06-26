import 'package:beyond_vision/core/constants.dart';
import 'package:beyond_vision/model/routine_model.dart';
import 'package:beyond_vision/provider/routine_provider.dart';
import 'package:beyond_vision/service/routine_service.dart';
import 'package:beyond_vision/ui/routine/widgets/new_routine_name.dart';
import 'package:beyond_vision/ui/routine/widgets/routine_detail.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class RoutineButton extends StatelessWidget {
  final Routine routine;
  final int index;

  const RoutineButton({super.key, required this.routine, required this.index});

  void showOptionsDialog(BuildContext context, int index) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          backgroundColor: const Color(boxColor),
          title: const Text(
            "이름 변경 및 삭제",
            style: TextStyle(
                color: Color(fontYellowColor),
                fontSize: 38,
                fontWeight: FontWeight.bold),
          ),
          content: SingleChildScrollView(
            child: ListBody(
              children: <Widget>[
                ListTile(
                  title: const Text(
                    '이름 변경',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 28,
                    ),
                  ),
                  onTap: () {
                    Navigator.of(context).pop();
                    showDialog(
                        context: context,
                        builder: (BuildContext context) =>
                            NewName(isExist: true, index: index));
                  },
                ),
                ListTile(
                  title: const Text(
                    '루틴 삭제',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 28,
                    ),
                  ),
                  onTap: () {
                    RoutineService routineService = RoutineService();
                    routineService.deleteRoutine(routine, 1);
                    RoutineProvider routineProvider =
                        Provider.of<RoutineProvider>(context, listen: false);
                    routineProvider.deleteRoutine(index);
                    Navigator.of(context).pop();
                  },
                ),
                ListTile(
                  title: const Text(
                    '취소',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 28,
                    ),
                  ),
                  onTap: () {
                    Navigator.of(context).pop();
                  },
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onLongPress: () {
        showOptionsDialog(context, index);
      },
      child: Container(
        margin: const EdgeInsets.all(10),
        child: ElevatedButton(
          style: ElevatedButton.styleFrom(
            padding: const EdgeInsets.all(10),
            backgroundColor: const Color(boxColor),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(30),
            ),
          ),
          onPressed: () {
            Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) =>
                        RoutineDetail(routine: routine, index: index)));
          },
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Text((index + 1).toString(),
                    style: const TextStyle(
                        color: Color(fontYellowColor),
                        fontWeight: FontWeight.w900,
                        fontSize: 64)),
                Text(
                  routine.routineName,
                  overflow: TextOverflow.ellipsis,
                  style: const TextStyle(
                      fontSize: 36,
                      color: Colors.white,
                      fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
