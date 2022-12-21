package com.example.projectnews

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL ="https://inshorts-news.vercel.app/"
    //CREATE HTTP CLIENT
    private val okHttp =OkHttpClient.Builder()

    //retrofit builder
    private val builder =Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create retrofit Instance
    private val retrofit = builder.build()


    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }

}
