package com.example.k_1919_2_myweatherapp_gb.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k_1919_2_myweatherapp_gb.R
import com.example.k_1919_2_myweatherapp_gb.databinding.MainFragmentBinding
import com.example.k_1919_2_myweatherapp_gb.viewmodel.MainViewModel


class MainFragment : Fragment() {

    lateinit var binding:MainFragmentBinding// утечка памяти

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        // return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOne.setOnClickListener{ }
        // view.findViewById<TextView>(R.id.btnOne).setOnClickListener { }
        // view.findViewById<Button>(R.id.btnOne).setOnClickListener { }

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object:Observer<Any>{
            override fun onChanged(data: Any) {
                renderData(data)
            }
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        viewModel.getWeather()
    }

    private fun renderData(data:Any) {
        Toast.makeText(requireContext(), "РАБОТАЕТ", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}