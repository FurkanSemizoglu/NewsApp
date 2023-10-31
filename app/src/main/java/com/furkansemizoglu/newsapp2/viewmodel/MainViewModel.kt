package com.furkansemizoglu.newsapp2.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
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
    val newsData = MutableLiveData<NewsModel>()

    val newsApiService = NewsAPIService()

    val newsError = MutableLiveData<Boolean>()

    val newsLoading = MutableLiveData<Boolean>()


    val disposable  = CompositeDisposable()


    fun getDataFromApi(){
        Log.w("getdata","tis okay")
        newsLoading.value = true

        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsModel>(){
                    override fun onSuccess(t: NewsModel) {
                        Log.w("listcheckcontorl","tis okay")
                       newsLoading.value = false
                       newsError.value = false
                       newsData.value = t

                        val myList : NewsModel
                        myList = t

                    }

                    override fun onError(e: Throwable) {
                        Log.e("refreshPictureOfDay", Log.getStackTraceString(e))
                        Log.w("listcheckme",e.printStackTrace().toString())
                        newsLoading.value = false
                        newsError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

