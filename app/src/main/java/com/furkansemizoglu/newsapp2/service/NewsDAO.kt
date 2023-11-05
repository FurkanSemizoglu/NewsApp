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
    suspend fun getNews() : List<NewsModelItem>




    @Query("SELECT * FROM News WHERE title LIKE :title")
    suspend fun findByTitle(title: String) : NewsModelItem



    @Insert
    suspend fun insertAll(vararg news: NewsModelItem ) : List<Long>

    @Delete
    suspend fun delete(news: NewsModelItem)

    @Query("DELETE FROM News")
    suspend fun deleteAllCountries ()

    @Update
    suspend fun updateTodo(vararg news: NewsModelItem)
}