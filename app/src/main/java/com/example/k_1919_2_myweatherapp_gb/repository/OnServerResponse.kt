package com.example.k_1919_2_myweatherapp_gb.repository

import com.example.k_1919_2_myweatherapp_gb.repository.dto.WeatherDTO

fun interface OnServerResponse {

    fun onResponse(weatherDTO: WeatherDTO)
}