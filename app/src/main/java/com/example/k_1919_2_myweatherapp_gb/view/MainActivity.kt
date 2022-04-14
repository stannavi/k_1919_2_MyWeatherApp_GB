package com.example.k_1919_2_myweatherapp_gb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import com.example.k_1919_2_myweatherapp_gb.R
import com.example.k_1919_2_myweatherapp_gb.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }
}