import 'package:assistant/util/app_constant.dart';
import 'package:flutter/services.dart';

final MethodChannel volumeChannel = MethodChannel(AppConstant.volumeChannel);

class ChannelService {
  static Future<int> increaseVolume() async {
    int currentVolume = 0;
    try {
      currentVolume =
          await volumeChannel.invokeMethod(AppConstant.increaseVolume);
      print('volume level $currentVolume');
    } on PlatformException catch (e) {
      print('Fail to set volume: $e');
    }
    return currentVolume;
  }

  static Future<int> decreaseVolume() async {
    int currentVolume = 0;
    try {
      currentVolume =
          await volumeChannel.invokeMethod(AppConstant.decreaseVolume);
      print('volume level $currentVolume');
    } on PlatformException catch (e) {
      print('Fail to set volume: $e');
    }
    return currentVolume;
  }
}
