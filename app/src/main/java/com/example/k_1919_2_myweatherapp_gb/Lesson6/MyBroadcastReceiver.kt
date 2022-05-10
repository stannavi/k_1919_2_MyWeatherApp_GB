package com.example.k_1919_2_myweatherapp_gb.Lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_SERVICE_MESSAGE

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?. let {
            val extra = it.getStringExtra(KEY_BUNDLE_SERVICE_MESSAGE)
            Log.d("@@@", "MyBroadcastReceiver on Receive $extra")
        }
    }
}