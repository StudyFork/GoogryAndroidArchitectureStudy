package com.example.kyudong3.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.kyudong3.R

class RecyclerViewItemDivider : ItemDecoration {
    private var generalDivider: Drawable?
    private var currentDivider: Drawable?
    private var hasFooter = false

    constructor(context: Context?) {
        generalDivider = ContextCompat.getDrawable(context!!, R.drawable.divider_rv)
        currentDivider = generalDivider
    }

    constructor(context: Context?, hasFooter: Boolean) {
        generalDivider = ContextCompat.getDrawable(context!!, R.drawable.divider_rv)
        currentDivider = generalDivider
        this.hasFooter = hasFooter
    }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            if (hasFooter && i == childCount - 2) {
                continue
            }
            val child = parent.getChildAt(i)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + generalDivider!!.intrinsicHeight

            currentDivider!!.setBounds(left, top, right, bottom)
            currentDivider!!.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect[0, 0, 0] = generalDivider!!.intrinsicHeight
    }
}