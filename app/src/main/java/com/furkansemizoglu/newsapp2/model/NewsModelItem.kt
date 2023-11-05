package com.furkansemizoglu.newsapp2.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("News")
data class NewsModelItem(
    @PrimaryKey(autoGenerate = true)
    public var id  : Int,

    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("content")
    val content: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("publishedAt")
    val publishedAt: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("urlToImage")
    val urlToImage: String
)