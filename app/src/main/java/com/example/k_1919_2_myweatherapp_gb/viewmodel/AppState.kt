package com.example.k_1919_2_myweatherapp_gb.viewmodel

import com.example.k_1919_2_myweatherapp_gb.repository.Weather

sealed class AppState {
    object Loading : AppState()
    data class Success(val weatherData: Weather) : AppState() {
        fun test() {}
    }

    data class Error(val error: Throwable) : AppState()
}
