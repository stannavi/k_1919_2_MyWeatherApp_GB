package com.example.k_1919_2_myweatherapp_gb.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L)
        return Weather()
    }

    override fun getWorldWeatherFromLocalStorage() = getWorldCities()

    override fun getRussianWeatherFromLocalStorage() = getRussianCities()

}