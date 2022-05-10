package com.example.k_1919_2_myweatherapp_gb.view

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.k_1919_2_myweatherapp_gb.Lesson6.MainService
import com.example.k_1919_2_myweatherapp_gb.Lesson6.MyBroadcastReceiver
import com.example.k_1919_2_myweatherapp_gb.R
import com.example.k_1919_2_myweatherapp_gb.Lesson6.ThreadsFragment
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_SERVICE_MESSAGE
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_WAVE
import com.example.k_1919_2_myweatherapp_gb.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }

        startService(Intent(this, MainService::class.java).apply {
            putExtra(KEY_BUNDLE_SERVICE_MESSAGE, "Привет, сервис!")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter(KEY_WAVE))
        //LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("myaction"))
    }

    //lesson 6
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_threads->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ThreadsFragment.newInstance()).commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}