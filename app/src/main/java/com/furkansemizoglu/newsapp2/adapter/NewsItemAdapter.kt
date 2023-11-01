package com.furkansemizoglu.newsapp2.adapter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListItemBinding
import com.furkansemizoglu.newsapp2.model.NewsModelItem
import com.furkansemizoglu.newsapp2.views.NewsListDirections


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

        /*
        val bundle = Bundle()
        bundle.putInt("itemPosition",position)


         */


        /*
        bundle.getParcelableArray("newsList" , newsList)

        bundle.getParcelableArrayList("newsList" , newsList)

         */

        holder.itemView.setOnClickListener {


            val action = NewsListDirections.actionNewsListToItemDetail( position)

            Navigation.findNavController(it).navigate(action )


            /*
            // Find the NavController and navigate to the specified destination
            it.findNavController().navigate(

                 NewsListDirections.actionNewsListToItemDetail()
            )

             */
        }

    }


    fun updateNewsList(newNewsList : List<NewsModelItem>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

}