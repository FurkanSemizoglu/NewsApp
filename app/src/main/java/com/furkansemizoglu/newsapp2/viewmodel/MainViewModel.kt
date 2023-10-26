package com.furkansemizoglu.newsapp2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansemizoglu.newsapp2.model.NewsModel
import com.furkansemizoglu.newsapp2.service.NewsAPIService
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class MainViewModel : ViewModel() {
    val newsData = MutableLiveData<List<NewsModel>>()

    val newsApiService = NewsAPIService()

    val newsError = MutableLiveData<Boolean>()

    val newsLoading = MutableLiveData<Boolean>()


    val disposable  = CompositeDisposable()


    fun getDataFromApi(){

        newsLoading.value = true

        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<NewsModel>>(){
                    override fun onSuccess(t: List<NewsModel>) {
                       newsLoading.value = false
                       newsError.value = false
                       newsData.value = t
                    }

                    override fun onError(e: Throwable) {
                        newsLoading.value = false
                        newsError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }



}

