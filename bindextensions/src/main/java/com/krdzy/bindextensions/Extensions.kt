package com.krdzy.bindextensions

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.Animation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.NumberFormat
import java.util.*

/**
 * Returns height of current screen.
 *
 * @return current screen height
 */
fun Context.getScreenHeight(): Int {
    val measuredHeight: Int
    val size = Point()
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    wm.defaultDisplay.getSize(size)
    measuredHeight = size.y

    return measuredHeight
}

/**
 * Returns width of current screen.
 *
 * @return current screen width in pixels
 */
fun Context.getcreenWidth(): Int {
    val measuredHeight: Int
    val size = Point()
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    wm.defaultDisplay.getSize(size)
    measuredHeight = size.x

    return measuredHeight
}

/**
 * Returns height of status bar on device.
 *
 * @return status bar height
 */
fun Activity.getStatusBarHeight(): Int {
    val displayRect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(displayRect)
    return displayRect.top
}

/**
 * Converts density pixels passed as arguments to pixels.
 *
 * @param dp      density pixel value
 *
 * @return value converted to pixels
 */
fun Context.dpToPx(dp: Int): Int = Math.round(dp * getPixelScaleFactor())

/**
 * Converts density pixels passed as arguments to pixels.
 *
 * @param dp      density pixel value
 *
 * @return value converted to pixels
 */
fun Context.dpToPx(dp: Float): Float = Math.round(dp * getPixelScaleFactor()).toFloat()

/**
 * Converts pixels passed as arguments to density pixels.
 *
 * @param px      pixel value
 *
 * @return value converted to density pixels
 */
fun Context.pxToDp(px: Int): Int = Math.round(px / getPixelScaleFactor())

/**
 * Calculates pixel scale factor which is needed for converting pixels to density pixels and vice versa.
 *
 * @return value of pixel scale factor
 */
private fun Context.getPixelScaleFactor(): Float {
    val displayMetrics = this.resources.displayMetrics
    return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
}

/**
 * Notifies the end of the animation. This callback is not invoked for animations with repeat count set to INFINITE.
 * Extension function on [Animation]
 *
 * @param onFinished lambda function invoked on animation end
 */
fun Animation.onAnimationFinished(onFinished: (animation: Animation) -> Unit = {}) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation) {
            onFinished.invoke(animation)
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
}

/**
 * Notifies the end of the animation. This callback is not invoked for animations with repeat count set to INFINITE.
 * Extension function on [ValueAnimator]
 *
 * @param onFinished lambda function invoked on animation end
 */
fun ValueAnimator.onAnimationFinished(onFinished: (animator: Animator?) -> Unit = {}) {
    this.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            onFinished.invoke(p0)
            p0?.removeAllListeners()
        }

        override fun onAnimationStart(p0: Animator?) {
        }

        override fun onAnimationCancel(p0: Animator?) {
        }
    })
}

/**
 * Override for += operator it has same function as calling [CompositeDisposable.add]
 *
 * @param disposable [Disposable] object to add to this instance of [CompositeDisposable]
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable?) {
    if (disposable != null) this.add(disposable)
}

/**
 * Returns this [Int] as formatted number based on default [Locale] value.
 *
 * @return [String] representation of this number
 */
fun Int.asFormattedNumber(): String = NumberFormat.getNumberInstance(Locale.getDefault()).format(this)

/**
 * Returns this [Long] as formatted number based on default [Locale] value.
 *
 * @return [String] representation of this number
 */
fun Long.asFormattedNumber(): String = NumberFormat.getNumberInstance(Locale.getDefault()).format(this)

/**
 * Returns this [Int] formatted as an English ordinal
 *
 * @return [String] ordinal representation of this number.
 */
fun Int.asOrdinal(): String = "$this" + if (this % 100 in 11..13) "th" else when (this % 10) {
    1 -> "st"
    2 -> "nd"
    3 -> "rd"
    else -> "th"
}
