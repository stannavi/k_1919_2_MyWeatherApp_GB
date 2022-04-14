package com.example.k_1919_2_myweatherapp_gb.view.weatherlist

import com.example.k_1919_2_myweatherapp_gb.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}