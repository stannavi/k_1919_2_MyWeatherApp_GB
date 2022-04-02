package com.example.k_1919_2_myweatherapp_gb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_1919_2_myweatherapp_gb.repository.Repository
import com.example.k_1919_2_myweatherapp_gb.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeather() {
        Thread {
            liveData.postValue(AppState.Loading)

            if ((0..10).random() > 5) {
                val answer = repository.getWeatherFromServer()
                //val answer = if(узнать локально или сервер) repository.getWeatherFromServer() else getWeatherFromLocalStorage()
                liveData.postValue(AppState.Success(answer))
            }
            else
                liveData.postValue(AppState.Error(IllegalAccessException()))
        }.start()
    }
}