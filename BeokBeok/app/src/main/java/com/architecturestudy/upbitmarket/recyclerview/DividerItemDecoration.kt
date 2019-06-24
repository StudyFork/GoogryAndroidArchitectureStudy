package com.architecturestudy.upbitmarket.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(
    private val divider: Drawable
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCnt = parent.childCount
        for (i in 0..childCnt) {
            val child = parent.getChildAt(i)
            val params = child?.layoutParams as? RecyclerView.LayoutParams
            val top = params?.bottomMargin?.let { child.bottom.plus(it) }
            val bottom = top?.plus(divider.intrinsicHeight)
            divider.setBounds(left, top ?: 0, right, bottom ?: 0)
            divider.draw(c)
        }
    }
}