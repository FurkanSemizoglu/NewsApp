package com.furkansemizoglu.newsapp2.adapter

import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListItemBinding


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkansemizoglu.newsapp2.model.NewsModelItem

class NewsItemAdapter ( val newsList : ArrayList<NewsModelItem>) :    RecyclerView.Adapter<NewsItemAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(val binding : FragmentNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val binding =FragmentNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  newsList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.newsStory.text =  newsList[position].content
        holder.binding.newsTitle.text = newsList[position].title

    }


    fun updateNewsList(newNewsList : List<NewsModelItem>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

}