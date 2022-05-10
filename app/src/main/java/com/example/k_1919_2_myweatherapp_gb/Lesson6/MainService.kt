package com.example.k_1919_2_myweatherapp_gb.Lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_SERVICE_MESSAGE
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_WAVE
import java.lang.Thread.sleep

class MainService(val name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work MainService")
        intent?.let{
            val extra = it.getStringExtra(KEY_BUNDLE_ACTIVITY_MESSAGE)
            Log.d("@@@", "work MainService $extra")
            sleep(1000L)
            val message = Intent(KEY_WAVE)
            message.putExtra(KEY_BUNDLE_SERVICE_MESSAGE, " привет активити, и тебе всего хорошего")
            sendBroadcast(message)
        }
    }
}