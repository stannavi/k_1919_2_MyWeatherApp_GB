package com.example.k_1919_2_myweatherapp_gb.repository


import com.google.gson.annotations.SerializedName

data class InfoDTO(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("url")
    val url: String
)