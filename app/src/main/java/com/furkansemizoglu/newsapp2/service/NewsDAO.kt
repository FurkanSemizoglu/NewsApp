package com.furkansemizoglu.newsapp2.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.furkansemizoglu.newsapp2.model.NewsModelItem


@Dao
interface NewsDAO {



    @Query("SELECT * FROM News")
    fun getNews() : List<NewsModelItem>




    @Query("SELECT * FROM News WHERE title LIKE :title")
    fun findByTitle(title: String) : NewsModelItem



    @Insert
    fun insertAll(vararg news: NewsModelItem )

    @Delete
    fun delete(news: NewsModelItem)

    @Query("DELETE FROM News")
    fun deleteAllCountries ()

    @Update
    fun updateTodo(vararg news: NewsModelItem)
}