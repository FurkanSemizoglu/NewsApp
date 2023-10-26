package com.furkansemizoglu.newsapp2.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkansemizoglu.newsapp2.adapter.NewsItemAdapter
import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListBinding
import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListItemBinding
import com.furkansemizoglu.newsapp2.model.Weather
import com.furkansemizoglu.newsapp2.viewmodel.MainViewModel


class NewsList : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNewsListBinding.inflate(layoutInflater)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Weathers = ArrayList<Weather>()

        Weathers.add(Weather("Ali"))
        Weathers.add(Weather("Cem"))
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        val adapter = NewsItemAdapter(Weathers)

        recyclerView.adapter = adapter


    }

}