package com.furkansemizoglu.newsapp2.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkansemizoglu.newsapp2.adapter.NewsItemAdapter
import com.furkansemizoglu.newsapp2.databinding.FragmentNewsListBinding
import com.furkansemizoglu.newsapp2.model.NewsModel
import com.furkansemizoglu.newsapp2.viewmodel.MainViewModel


class NewsList : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private lateinit var viewModel : MainViewModel
    private  var newsItemAdapter =  NewsItemAdapter(arrayListOf())
    private lateinit var apiResultsList: ArrayList<NewsModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNewsListBinding.inflate(layoutInflater)
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

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapterConnector()

        viewModel.getDataFromApi()

        observeLiveData()

    }


    private fun adapterConnector(){

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = newsItemAdapter
    }


    private fun observeLiveData(){
        Log.w("okay1","ok")
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
          //  var number = apiResultsList.size
          //  Log.w("okaymı1" ,Integer.toString(number) )
            it?.let {

                /*
                Log.w("okaymı1" ,apiResultsList.size.toString() )

                 */
                for (i in  0..it.articles.size){
                   apiResultsList.add(it)
                }
                /*
                Log.w("okaymı2" ,apiResultsList.size.toString() )
                */
                newsItemAdapter.updateNewsList(apiResultsList)
            }
        })


        viewModel.newsError.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.recyclerView.visibility = View.GONE
                binding.errorTextView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
            else{
                binding.recyclerView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }

        })


        viewModel.newsLoading.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.recyclerView.visibility = View.GONE
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.recyclerView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })
    }




}