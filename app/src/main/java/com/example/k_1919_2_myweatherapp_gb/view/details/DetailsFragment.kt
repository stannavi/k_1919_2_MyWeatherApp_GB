package com.example.k_1919_2_myweatherapp_gb.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.k_1919_2_myweatherapp_gb.databinding.FragmentDetailsBinding
import com.example.k_1919_2_myweatherapp_gb.repository.Weather
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            renderData(it)
        }
    }

    private fun renderData(weather: Weather) {
        with(binding) {
            loadingLayout.visibility = View.GONE
            cityName.text = weather.city.name.toString()
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelslike.toString()
            cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
        }
        mainView.showSnackBar()
     }

    fun View.showSnackBar() {
        Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}



