package com.example.k_1919_2_myweatherapp_gb.repository

fun interface OnServerResponse {

    fun onResponse(weatherDTO: WeatherDTO)
}