package com.krdzy.bindextensions.bindings.views

import android.databinding.BindingAdapter
import android.widget.TextView

/**
 * Binding adapters for [TextView].
 */
object TextViewBindings {

    /**
     * Binds text represented by passed resource ID.
     */
    @BindingAdapter("android:text")
    @JvmStatic
    fun bindText(textView: TextView, resourceId: Int) {
        textView.text = textView.context.getString(resourceId)
    }
}