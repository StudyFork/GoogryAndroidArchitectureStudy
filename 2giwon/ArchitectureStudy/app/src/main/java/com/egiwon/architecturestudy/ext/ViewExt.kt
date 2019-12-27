package com.egiwon.architecturestudy.ext

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.egiwon.architecturestudy.R

fun View.doOnApplyWindowInsets(
    block: (View, WindowInsets, InitialPadding, InitialMargin) -> Unit
) {
    val initialPadding = recordInitialPaddingForView(this)
    val initialMargin = recordInitialMarginForView(this)

    setOnApplyWindowInsetsListener { v, insets ->
        block(v, insets, initialPadding, initialMargin)
        insets
    }
    requestApplyInsetsWhenAttached()
}

class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int)


private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

private fun recordInitialMarginForView(view: View): InitialMargin {
    val lp = view.layoutParams as? ViewGroup.MarginLayoutParams
        ?: throw IllegalArgumentException(view.context.getString(R.string.layout_error_params))
    return InitialMargin(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin)
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

fun View.getColor(@ColorRes colorRes: Int): Int = context.getColor(colorRes)

fun View.getColorStateList(@ColorRes colorRes: Int): ColorStateList =
    context.getColorStateList(colorRes)

@BindingAdapter("onClick")
fun View.startWebBrowser(url: String) {
    setOnClickListener {
        url.let { url ->
            ContextCompat.startActivity(
                context,
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(url)
                ),
                null
            )
        }
    }

}


