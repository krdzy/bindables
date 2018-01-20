package com.krdzy.bindextensions.bindings.views

import android.databinding.BindingAdapter
import android.widget.Button
import android.widget.TextView

/**
 * Binding adapters for [Button].
 */
object ButtonViewBindings {

    /**
     * Binds text represented by passed resource ID.
     */
    @BindingAdapter("android:text")
    @JvmStatic
    fun bindButtonText(button: Button, resourceId: Int) {
        button.text = button.context.getString(resourceId)
    }
}