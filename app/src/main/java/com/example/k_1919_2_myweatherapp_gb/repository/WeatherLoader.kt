package com.example.k_1919_2_myweatherapp_gb.repository

import android.os.Handler
import android.os.Looper
import com.example.k_1919_2_myweatherapp_gb.BuildConfig
import com.example.k_1919_2_myweatherapp_gb.repository.dto.WeatherDTO
import com.example.k_1919_2_myweatherapp_gb.utils.YANDEX_API_KEY
import com.example.k_1919_2_myweatherapp_gb.utils.YANDEX_DOMAIN
import com.example.k_1919_2_myweatherapp_gb.utils.YANDEX_PATH
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {


    fun loadWeather(lat: Double, lon: Double) {

        val urlText = "$YANDEX_DOMAIN${YANDEX_PATH}lat=$lat&lon=$lon"
        val uri = URL(urlText)

        Thread {
            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(
                        YANDEX_API_KEY,
                        BuildConfig.WEATHER_API_KEY
                    )
                }

            try {
                Thread.sleep(500)
                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val responseMessage = urlConnection.responseMessage

                val serverside = 500..599
                val clientside = 400..499
                val responseOK = 200..299

                when (responseCode) {
                    in serverside -> {

                    }
                    in clientside -> {

                    }
                    in responseOK -> {
                        val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))

                        val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                        Handler(Looper.getMainLooper()).post {
                            onServerResponseListener.onResponse(weatherDTO)
                        }
                    }
                }


            } catch (e: JsonSyntaxException) {

            } finally {
                urlConnection.disconnect()
            }
        }.start()

    }

}