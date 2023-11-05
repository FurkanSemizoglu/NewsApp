package com.furkansemizoglu.newsapp2.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkansemizoglu.newsapp2.model.NewsModelItem
import com.furkansemizoglu.newsapp2.service.NewsAPIService
import com.furkansemizoglu.newsapp2.service.NewsDatabase
import com.furkansemizoglu.newsapp2.utils.CustomSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(application : Application) : AndroidViewModel(application) {
    val newsData = MutableLiveData<List<NewsModelItem>>()

    val newsApiService = NewsAPIService()

    val newsError = MutableLiveData<Boolean>()

    val newsLoading = MutableLiveData<Boolean>()


    val disposable  = CompositeDisposable()

    val db = NewsDatabase(getApplication()).newsDao()


    private var customPreferences = CustomSharedPreferences(getApplication())

    private var refreshTime = 1 * 60 * 1000 * 1000 * 1000L

    fun refreshData(){

        val updateTime = customPreferences.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime()  - updateTime < refreshTime  ){
            getDataFromSQLite()
        }else{
            getDataFromApi()
        }

    }
    private fun getDataFromSQLite(){
        newsLoading.value = true
        viewModelScope.launch {
            val countries = NewsDatabase(getApplication()).newsDao().getNews()
            showNews(countries)
            Toast.makeText(getApplication(),"Countries From SQLite", Toast.LENGTH_LONG).show()
            Log.w("api","Datas coming from SQLite")
        }
    }

    fun storeInSQLite ( list : List<NewsModelItem> ) {


        viewModelScope.launch {

            db.deleteAllCountries()

            val allList  = db.insertAll(*list.toTypedArray())

            var i = 0

            while (i < list.size){
                list[i].id = allList[i].toInt()
                i += 1
            }
        }


        showNews(list)
    }

    fun getDataFromApi(){
        Log.w("getdata","tis okay")
        newsLoading.value = true


        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<NewsModelItem>>(){
                    override fun onSuccess(t: List<NewsModelItem>) {
                        storeInSQLite(t)
                        showNews(t)
                        Toast.makeText(getApplication(),"Countries From API", Toast.LENGTH_LONG).show()
                        Log.w("api","Datas coming from API")
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

    private fun showNews(countryList: List<NewsModelItem>) {
        newsData.value = countryList
        newsError.value = false
        newsLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

