package com.example.k_1919_2_myweatherapp_gb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.k_1919_2_myweatherapp_gb.R
import com.example.k_1919_2_myweatherapp_gb.view.details.ThreadsLesson6.ThreadsFragment
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