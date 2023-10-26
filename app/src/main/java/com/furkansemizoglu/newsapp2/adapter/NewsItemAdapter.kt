package com.furkansemizoglu.newsapp2.adapter

import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListItemBinding
import com.furkansemizoglu.newsapp2.model.Weather



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsItemAdapter (private val weatherList : ArrayList<Weather>) :    RecyclerView.Adapter<NewsItemAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(val binding : FragmentNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val binding =FragmentNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        holder.binding.newsStory.text =  weatherList[position].name
    }



}