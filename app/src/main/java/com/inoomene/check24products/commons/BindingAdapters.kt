package com.inoomene.check24products.commons


import android.webkit.WebSettings
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.inoomene.check24products.R


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {

    Glide.with(imageView.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_launcher)
        .into(imageView)
}






