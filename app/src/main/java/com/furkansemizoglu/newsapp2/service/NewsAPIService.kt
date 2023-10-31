package com.furkansemizoglu.newsapp2.service

import com.furkansemizoglu.newsapp2.model.NewsModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {


    private val api = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)



    fun getData() : Single<NewsModel> {
        return api.getNewsData()
    }
}