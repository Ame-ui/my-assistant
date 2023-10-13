import 'package:assistant/service/channel_service.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int volume = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          IconButton(
              onPressed: () async {
                volume = await ChannelService.decreaseVolume();
                setState(() {});
              },
              icon: const Icon(Icons.remove)),
          Text('$volume'),
          IconButton(
              onPressed: () async {
                volume = await ChannelService.increaseVolume();
                setState(() {});
              },
              icon: const Icon(Icons.add))
        ],
      )),
    );
  }
}
