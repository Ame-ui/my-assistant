package com.example.assistant

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.media.AudioManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES

class MainActivity : FlutterActivity() {
    // private val CHANNEL = "samples.flutter.dev/battery"
    private val CHANNEL = "volume_channel"
    // override fun onCreate(savedInstanceState: Bundle?) {
    //         super.onCreate(savedInstanceState)
    //         audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    //     }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            val maxVolume = getMaxVolume()
            val currentVol = getCurrentVolume()
            
            if (call.method == "increaseVolume") {
              if(currentVol < maxVolume)
              {
                setVolume(currentVol +1)
                if (currentVol != -1) {
                  val current = getCurrentVolume()
                  result.success(current)
                } else {
                  result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            }
            } else if (call.method == "decreaseVolume"){
              if(currentVol > 0)
              {
                setVolume(currentVol -1)
                if (currentVol != -1) {
                  val current = getCurrentVolume()
                  result.success(current)
                } else {
                  result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            }
            } else{
                result.notImplemented()

            }
        // This method is invoked on the main thread.
        // TODO
        }
    }
    private fun getMaxVolume(): Int {
      val volumeLevel: Int
      val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
      volumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
      return volumeLevel
    }

    private fun getCurrentVolume(): Int {
        val volumeLevel: Int
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        return volumeLevel
      }
    private fun setVolume(volume: Int){
      val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
      val volumeLevel = audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0)
      return volumeLevel
    }
    
    // private lateinit var audioManager: AudioManager

    // override fun onCreate(savedInstanceState: Bundle?) {
    //     super.onCreate(savedInstanceState)
    //     audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    // }

    // fun setVolume(volume: Double) {
    //     val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    //     val newVolume = (volume * maxVolume).toInt()
    //     audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
    // }
}
