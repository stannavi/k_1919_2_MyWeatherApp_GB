package com.example.k_1919_2_myweatherapp_gb.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.k_1919_2_myweatherapp_gb.databinding.FragmentDetailsBinding
import com.example.k_1919_2_myweatherapp_gb.repository.OnServerResponse
import com.example.k_1919_2_myweatherapp_gb.repository.Weather
import com.example.k_1919_2_myweatherapp_gb.repository.WeatherDTO
import com.example.k_1919_2_myweatherapp_gb.repository.WeatherLoader
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(), OnServerResponse {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    lateinit var currentCityName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
            //Thread {
                WeatherLoader(this@DetailsFragment).loadWeather(it.city.lat, it.city.lon)
            //}.start()
        }
    }

    private fun renderData(weather: WeatherDTO) {
        with(binding) {
            loadingLayout.visibility = View.GONE
            cityName.text = currentCityName
            temperatureValue.text = weather.factDTO.temperature.toString()
            feelsLikeValue.text = weather.factDTO.feelsLike.toString()
            cityCoordinates.text = "${weather.infoDTO.lat} ${weather.infoDTO.lon}"
        }

        Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
        //mainView.showSnackBar()
    }

    fun View.showSnackBar() {

    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResponse(weatherDTO: WeatherDTO) {
        renderData(weatherDTO)

    }
}



