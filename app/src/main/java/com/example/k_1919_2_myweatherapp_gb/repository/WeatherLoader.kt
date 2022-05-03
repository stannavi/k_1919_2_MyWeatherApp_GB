package com.example.k_1919_2_myweatherapp_gb.repository

import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.k_1919_2_myweatherapp_gb.BuildConfig
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {


    fun loadWeather(lat: Double, lon: Double) {

        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpsURLConnection =
            (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000
                readTimeout = 1000
                addRequestProperty(
                    "X-Yandex-API-Key",
                    BuildConfig.WEATHER_API_KEY
                )
            }

        Thread {
            try {
                val headers = urlConnection.headerFields
                val response = urlConnection.responseCode
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))

                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    onServerResponseListener.onResponse(weatherDTO)
                }
            } catch (e: Exception) {
                // TODO HW Snackbar "что-то пошло не так"
            } finally {
                urlConnection.disconnect()
            }

            //urlConnection.disconnect()
        }.start()
    }
}