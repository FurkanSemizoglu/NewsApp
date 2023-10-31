package com.furkansemizoglu.newsapp2.service

import com.furkansemizoglu.newsapp2.model.NewsModelItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {

// old_api = https://newsapi.org/

    private val api = Retrofit.Builder()
        .baseUrl("https://furkansemizoglu.github.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)



    fun getData() : Single<List<NewsModelItem>> {
        return api.getNewsData()
    }
}