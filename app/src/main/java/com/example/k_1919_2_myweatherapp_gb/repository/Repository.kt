package com.example.k_1919_2_myweatherapp_gb.repository

interface Repository {
    fun getWeatherFromServer():Weather
    fun getWorldWeatherFromLocalStorage():List<Weather>
    fun getRussianWeatherFromLocalStorage():List<Weather>

}