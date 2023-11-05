package com.furkansemizoglu.newsapp2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansemizoglu.newsapp2.model.NewsModelItem
import com.furkansemizoglu.newsapp2.service.NewsAPIService
import com.furkansemizoglu.newsapp2.service.NewsDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application : Application) : AndroidViewModel(application) {
    val newsData = MutableLiveData<List<NewsModelItem>>()

    val newsApiService = NewsAPIService()

    val newsError = MutableLiveData<Boolean>()

    val newsLoading = MutableLiveData<Boolean>()


    val disposable  = CompositeDisposable()

    val db = NewsDatabase(getApplication()).newsDao()





    fun getDataFromApi(){
        Log.w("getdata","tis okay")
        newsLoading.value = true


        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<NewsModelItem>>(){
                    override fun onSuccess(t: List<NewsModelItem>) {
                        Log.w("listcheckcontorl","tis okay")
                       newsLoading.value = false
                       newsError.value = false
                       newsData.value = t



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

