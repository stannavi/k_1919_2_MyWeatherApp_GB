package com.example.k_1919_2_myweatherapp_gb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.k_1919_2_myweatherapp_gb.databinding.ActivityMainWebviewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {
    lateinit var binding: ActivityMainWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener {
            val urlText = binding.etTextUrl.text.toString()
            val uri = URL(urlText)
            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                }

            Thread {
                val headers = urlConnection.headerFields
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = getLinesAsOneBigString(buffer)
                // binding.webview.loadUrl(urlText)
                /*runOnUiThread { // 1 способ
                    binding.webview.loadData(result, "text/html; utf-8", "utf-8")
                }*/
                Handler(Looper.getMainLooper()).post { // 2 способ
                    //binding.webview.loadData(result, "text/html; utf-8", "utf-8")
                    binding.webview.settings.javaScriptEnabled = true
                    binding.webview.loadDataWithBaseURL(
                        null,
                        result,
                        "text/html; utf-8",
                        "utf-8",
                        null
                    )
                }
            }.start()
        }
    }

    private fun getLinesAsOneBigString(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}