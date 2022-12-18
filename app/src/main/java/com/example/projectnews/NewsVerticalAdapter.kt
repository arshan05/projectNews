package com.example.projectnews

import android.animation.LayoutTransition
import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectnews.databinding.NewsItemBinding
import com.squareup.picasso.Picasso
import java.net.URI

//class NewsVerticalAdapter(val onItemClicked: OnItemClicked):ListAdapter<Data, NewsViewHolder>(DiffCallback) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.news_item,parent,false)
//
//        return NewsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        holder.bind(getItem(position), onItemClicked)
//    }
//}
//
//private val DiffCallback = object: DiffUtil.ItemCallback<Data>(){
//    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
//        return oldItem.title == newItem.title
//    }
//
//    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
//        return oldItem == newItem
//    }
//
//}
////
////
//class NewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
//    val textToDisplayTitle = itemView.findViewById<TextView>(R.id.textToDisplayTitle)
//    val textToDisplayNews = itemView.findViewById<TextView>(R.id.textToDisplayNews)
//    val textToDisplayAuthor = itemView.findViewById<TextView>(R.id.textToDisplayAuthor)
//    val imageToDisplay = itemView.findViewById<ImageView>(R.id.imageToDisplay)
//    val readMoreBtn = itemView.findViewById<Button>(R.id.readMoreBtn)
//    fun bind(newsModel:Data, onItemClicked: OnItemClicked){
//        textToDisplayTitle.text = newsModel.title
//        textToDisplayNews.text = newsModel.decription
//        textToDisplayAuthor.text = newsModel.author
//        Picasso.get().load(newsModel.images).resize(1000,700)
//            .centerCrop().into(imageToDisplay)
//
//        readMoreBtn.setOnClickListener{
//            onItemClicked.onItemClicked(newsModel)
//        }
//
//        itemView.setOnClickListener(){
//            if(textToDisplayNews.visibility == View.GONE) {
//                textToDisplayNews.visibility = View.VISIBLE
//                readMoreBtn.visibility = View.VISIBLE
//            } else {
//                textToDisplayNews.visibility = View.GONE
//                readMoreBtn.visibility = View.GONE
//            }
//        }
//
//    }
//}

class NewsVerticalAdapter(val onItemClicked: OnItemClicked):ListAdapter<Data, NewsViewHolder>(DiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.viewDataBinding.newsModel = getItem(position)
        holder.viewDataBinding.cv.setOnClickListener(){
            if(holder.viewDataBinding.textToDisplayNews.visibility == View.GONE) {
                holder.viewDataBinding.textToDisplayNews.visibility = View.VISIBLE
                holder.viewDataBinding.readMoreBtn.visibility = View.VISIBLE
            } else {
                holder.viewDataBinding.textToDisplayNews.visibility = View.GONE
                holder.viewDataBinding.readMoreBtn.visibility = View.GONE
            }
        }
        holder.viewDataBinding.readMoreBtn.setOnClickListener{
            onItemClicked.onItemClicked(getItem(position))
        }
    }

}
private val DiffCallback = object: DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}
class NewsViewHolder(val viewDataBinding: NewsItemBinding):RecyclerView.ViewHolder(viewDataBinding.root){

}

interface OnItemClicked{
    fun onItemClicked(newsModel: Data)
}