package com.krdzy.bindextensions.bindings

import android.support.v4.view.ViewPager
import android.view.View
import android.databinding.BindingAdapter
import android.support.constraint.ConstraintLayout
import com.krdzy.bindextensions.dpToPx

/**
 * Global [BindingAdapter]s .
 */
object ViewBindings {

    /**
     * [BindingAdapter] for binding [View.OnClickListener] to [ConstraintLayout].
     */
    @BindingAdapter("android:onClick")
    @JvmStatic
    fun bindClickListener(view: ConstraintLayout, onClickListener: View.OnClickListener) {
        view.setOnClickListener(onClickListener)
    }

    /**
     * [BindingAdapter] for binding height property to view.
     *
     */
    @BindingAdapter("android:layout_height")
    @JvmStatic
    fun bindHeight(view: View, height: Int) {
        view.layoutParams.height = view.context.dpToPx(height)
    }

    /**
     * [BindingAdapter] for binding [ViewPager.OnPageChangeListener] to [ViewPager].
     */
    @BindingAdapter("app:onPageChangeListener")
    @JvmStatic
    fun bindOnPageChangeListener(pager: ViewPager, listener: ViewPager.OnPageChangeListener) {
        pager.setOnPageChangeListener(listener)
    }

}