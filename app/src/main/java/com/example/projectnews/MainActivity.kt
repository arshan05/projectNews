package com.example.projectnews

import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnItemClicked {
    var dataSet = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNews()


//        val newsVerticalView = findViewById<RecyclerView>(R.id.newsVerticalView)
//
//
//        newsVerticalView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
//        val adapter = NewsVerticalAdapter()
//        adapter.submitList(dataSet)
//        newsVerticalView.adapter = adapter
    }

    private fun loadNews() {
        val destinationService = ServiceBuilder.buildService(RetrofitApi::class.java)
        val requestCall = destinationService.getAllNews()
        requestCall.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val dataResponse = response.body()!!
                    dataSet = dataResponse.data
                    val newsVerticalView = findViewById<RecyclerView>(R.id.newsVerticalView)
                    newsVerticalView.layoutManager =
                        LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                    val adapter = NewsVerticalAdapter(this@MainActivity)
                    adapter.submitList(dataSet)
                    newsVerticalView.adapter = adapter
                } else {
                    Toast.makeText(applicationContext, "failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(applicationContext, "not went well", Toast.LENGTH_SHORT).show()
            }


        })
    }

    override fun onItemClicked(newsModel: Data) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsModel.readMore))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
