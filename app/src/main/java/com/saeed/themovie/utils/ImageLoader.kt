package com.saeed.themovie.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {
    companion object {

        private const val BASE_IMG_URL_W300 = "https://image.tmdb.org/t/p/w300"
        private const val BASE_IMG_URL_W500 = "https://image.tmdb.org/t/p/w500"

        fun load(url: String, imageView: ImageView) {
            Picasso.get().load(BASE_IMG_URL_W300 + url).into(imageView)
        }

        fun loadW500(url: String, imageView: ImageView) {
            Picasso.get().load(BASE_IMG_URL_W500 + url).into(imageView)
        }

        fun load(url: String, imageView: ImageView, placeHolder: Drawable) {
            Picasso.get().load(BASE_IMG_URL_W300 + url).placeholder(placeHolder).into(imageView)
        }

        fun load(uri: Uri, imageView: ImageView) {
            Picasso.get().load(uri).into(imageView)
        }
    }
}