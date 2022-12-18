package com.example.projectnews

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url:String){
    Picasso.get().load(url).resize(1500,800)
        .centerCrop()
        .into(this)
}