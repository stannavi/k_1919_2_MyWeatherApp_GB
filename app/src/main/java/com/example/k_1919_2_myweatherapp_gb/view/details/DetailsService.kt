package com.example.k_1919_2_myweatherapp_gb.view.details

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.k_1919_2_myweatherapp_gb.BuildConfig
import com.example.k_1919_2_myweatherapp_gb.repository.dto.WeatherDTO
import com.example.k_1919_2_myweatherapp_gb.utils.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailsService(val name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work DetailsService")
        intent?.let {
            val lat = it.getDoubleExtra(KEY_BUNDLE_LAT, 0.0)
            val lon = it.getDoubleExtra(KEY_BUNDLE_LON, 0.0)
            Log.d("@@@", "work DetailsService  $lat $lon")

            val urlText = "$YANDEX_DOMAIN${YANDEX_PATH}lat=$lat&lon=$lon"
            val uri = URL(urlText)

            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(
                        YANDEX_API_KEY,
                        BuildConfig.WEATHER_API_KEY
                    )
                }

            Thread.sleep(500)
            val headers = urlConnection.headerFields
            val responseCode = urlConnection.responseCode
            val responseMessage = urlConnection.responseMessage

            //TODO HW сделать то же, что и в WeatherLoader


            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)

            val message = Intent(KEY_WAVE_SERVICE_BROADCAST)
            message.putExtra(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER, weatherDTO)
           // sendBroadcast(message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(message)
        }
    }
}
