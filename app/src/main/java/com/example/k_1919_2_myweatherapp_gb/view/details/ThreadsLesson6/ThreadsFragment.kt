package com.example.k_1919_2_myweatherapp_gb.view.details.ThreadsLesson6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_1919_2_myweatherapp_gb.R
import com.example.k_1919_2_myweatherapp_gb.databinding.FragmentThreadsBinding
import com.example.k_1919_2_myweatherapp_gb.databinding.FragmentWeatherListBinding
import com.example.k_1919_2_myweatherapp_gb.repository.Weather
import com.example.k_1919_2_myweatherapp_gb.utils.KEY_BUNDLE_WEATHER
import com.example.k_1919_2_myweatherapp_gb.view.details.DetailsFragment
import com.example.k_1919_2_myweatherapp_gb.view.weatherlist.OnItemListClickListener
import com.example.k_1919_2_myweatherapp_gb.viewmodel.AppState
import com.example.k_1919_2_myweatherapp_gb.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_threads.*
import java.lang.Thread.sleep


class ThreadsFragment : Fragment() {

    private var _binding: FragmentThreadsBinding? = null
    private val binding: FragmentThreadsBinding
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
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myThreads = MyThreads()
        with(binding) {
            button.setOnClickListener {
                Thread {
                    val time = editText.text.toString().toLong()
                    sleep(time * 1000L)
                    requireActivity().runOnUiThread {
                        textView.text = "Плотно поработали ${time} сек"
                    }
                }.start()
            }
        }
    }

    class MyThreads: Thread() {
        lateinit var mHandler: Handler
        override fun run() {
            Looper.prepare()
            mHandler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThreadsFragment()
    }
}



