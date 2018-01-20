package com.krdzy.bindextensions.bindings.views

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Binding adapters for [ImageView].
 */
object ImageViewBindings {

    /**
     * Binds drawable represented by resource ID to [ImageView].
     */
    @BindingAdapter("app:srcCompat")
    @JvmStatic
    fun bindDrawable(imageView: ImageView, resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    /**
     * Binds drawable from passed [url] to [ImageView] using [Glide][https://github.com/bumptech/glide] library.
     */
    @BindingAdapter("app:srcCompat")
    @JvmStatic
    fun bindDrawable(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).apply(RequestOptions.centerCropTransform()).into(imageView)
    }
}