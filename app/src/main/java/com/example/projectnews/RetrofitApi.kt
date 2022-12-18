package com.example.projectnews

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/all")
    fun getAllNews() : Call<NewsModel>
}