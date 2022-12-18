package com.example.projectnews


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("author")
    val author: String,
    @SerializedName("decription")
    val decription: String,
    @SerializedName("images")
    val images: String,
    @SerializedName("inshorts-link")
    val inshortsLink: String,
    @SerializedName("read-more")
    val readMore: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String
)