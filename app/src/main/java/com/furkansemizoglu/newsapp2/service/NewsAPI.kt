package com.furkansemizoglu.newsapp2.service

import com.furkansemizoglu.newsapp2.model.NewsModelItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NewsAPI {

    /*
    val  new_api_url  = https://furkansemizoglu.github.io/jsonNews/newsApi.json
     */


/*
    const val API_KEY = "11d436cf76d6450691909c47bc194ea0"

https://newsapi.org/v2/everything?q=Apple&from=2023-10-18&sortBy=popularity&apiKey=11d436cf76d6450691909c47bc194ea0
    const val API_BASE_URL = "https://newsapi.org/v2/everything?q=Apple&from=2023-10-18&sortBy=popularity&apiKey=11d436cf76d6450691909c47bc194ea0/"

    const val API_URL ="v2/everything?q=Apple&from=2023-10-18&sortBy=popularity&apiKey=11d436cf76d6450691909c47bc194ea0"

 */
    @GET("jsonNews/newsApi.json")
    fun getNewsData() : Single<List<NewsModelItem>>
}