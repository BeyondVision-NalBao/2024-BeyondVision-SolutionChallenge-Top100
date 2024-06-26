import 'package:beyond_vision/core/constants.dart';
import 'package:beyond_vision/model/record_model.dart';
import 'package:beyond_vision/ui/home/home.dart';
import 'package:flutter/material.dart';
import 'package:flutter_tts/flutter_tts.dart';

class WorkoutResultPage extends StatefulWidget {
  final List<Record> results;
  const WorkoutResultPage({super.key, required this.results});

  @override
  State<WorkoutResultPage> createState() => _WorkoutResultPageState();
}

class _WorkoutResultPageState extends State<WorkoutResultPage> {
  FlutterTts tts = FlutterTts();

  @override
  void initState() {
    // TODO: implement initState
    tts.setLanguage('ko-KR');
    tts.setSpeechRate(0.8);

    super.initState();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    tts.stop();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    String getSum() {
      int sum = 0;
      for (int i = 0; i < widget.results.length; i++) {
        sum += widget.results[i].exerciseTime!;
      }
      int min = sum ~/ 60;
      int sec = sum % 60;

      return "$min 분 $sec 초";
    }

    // WorkoutProvider workoutProvider = Provider.of<WorkoutProvider>(context);
    // WorkOutService workoutService = WorkOutService();
    tts.speak("운동이 끝났습니다.");
    return Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child:
            Column(mainAxisAlignment: MainAxisAlignment.spaceAround, children: [
          const Text("운동 결과",
              style: TextStyle(
                  color: Color(fontYellowColor),
                  fontSize: 44,
                  fontWeight: FontWeight.bold)),
          Padding(
            padding: const EdgeInsets.all(30.0),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    const Text("총 운동시간",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 36,
                            fontWeight: FontWeight.bold)),
                    Text(getSum(),
                        style:
                            const TextStyle(color: Colors.white, fontSize: 32))
                  ],
                ),
                const SizedBox(height: 20),
                const Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Text("운동 성공 횟수",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 36,
                            fontWeight: FontWeight.bold)),
                  ],
                ),
                const SizedBox(height: 10),
                Padding(
                  padding: const EdgeInsets.only(left: 20.0),
                  child: Column(
                      children: widget.results
                          .map((e) => Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    e.exerciseName!,
                                    style: const TextStyle(
                                        color: Colors.white, fontSize: 28),
                                  ),
                                  Text(
                                    "${e.successCount!.toString()} 회",
                                    style: const TextStyle(
                                        color: Colors.white, fontSize: 28),
                                  ),
                                ],
                              ))
                          .toList()),
                ),
              ],
            ),
          ),
          ElevatedButton(
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.all(15),
                backgroundColor: const Color(boxColor),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30),
                ),
              ),
              onPressed: () {
                Navigator.pop(context);
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => HomePage()),
                );
              },
              child: const Text("홈 화면으로 돌아가기",
                  style:
                      TextStyle(color: Color(fontYellowColor), fontSize: 28)))
        ]),
      ),
    );
  }
}
