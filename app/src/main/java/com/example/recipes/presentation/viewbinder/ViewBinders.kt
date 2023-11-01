package com.example.recipes.presentation.viewbinder

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.bindSrcUrl(url: String) {
    Glide
        .with(this)
        .load(url)
        .centerInside()
        .into(this)
}
