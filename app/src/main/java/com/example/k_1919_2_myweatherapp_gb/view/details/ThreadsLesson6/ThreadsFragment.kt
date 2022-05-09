package com.example.k_1919_2_myweatherapp_gb.view.details.ThreadsLesson6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        myThreads.start()
        with(binding) {
            val time = editText.text.toString().toLong()
            var counter = 0
            button1.setOnClickListener {
                Thread {
                    sleep(time * 1000L)
                    requireActivity().runOnUiThread {
                        textView1.text = "${time} сек. 1"
                    }
                    Handler(Looper.getMainLooper()).post {
                        textView1.text = "${time} сек. 2"
                        createTextView("${Thread.currentThread().name} ${++counter}")
                    }
            }.start()
        }

        //"вечный поток"
        button2.setOnClickListener {
            myThreads.mHandler?.post {
                sleep(time * 1000L)
                Handler(Looper.getMainLooper()).post {
                    textView2.text = "${time} сек. 2"
                    createTextView("${Thread.currentThread().name} ${++counter}")
                }
            }
        }
    }
}

    private fun createTextView(name: String) {
       binding.mainContainer.addView(TextView(requireContext()).apply {
            text = name
            textSize = 14f
    })
}

    class MyThreads: Thread() {
        var mHandler: Handler? = null
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



