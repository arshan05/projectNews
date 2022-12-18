package com.example.projectnews


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("category")
    val category: String,
    @SerializedName("count-articles")
    val countArticles: Int,
    @SerializedName("data")
    val `data`: MutableList<Data>
)