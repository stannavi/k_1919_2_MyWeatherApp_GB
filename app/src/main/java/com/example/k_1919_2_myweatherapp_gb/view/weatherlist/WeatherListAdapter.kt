package com.example.k_1919_2_myweatherapp_gb.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_1919_2_myweatherapp_gb.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_1919_2_myweatherapp_gb.repository.Weather

class WeatherListAdapter(
    private val onItemListClickListener: OnItemListClickListener,
    private var data: List<Weather> = listOf()
) :
    RecyclerView.Adapter<WeatherListAdapter.CityHolder>() {

    fun setData(dataNew: List<Weather>) {
        this.data = dataNew
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size

    inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            val binding = FragmentWeatherListRecyclerItemBinding.bind(itemView)
            binding.tvcityName.text = weather.city.name
            binding.root.setOnClickListener {
                onItemListClickListener.onItemClick(weather)
            }
        }
    }
}