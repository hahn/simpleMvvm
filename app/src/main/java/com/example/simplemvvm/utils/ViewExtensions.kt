package com.example.simplemvvm.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.simplemvvm.R

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fallback(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .dontAnimate()
        .into(this)
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}