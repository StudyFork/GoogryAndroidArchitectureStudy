package com.jay.architecturestudy.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val bottom: Int,
    private val spacing: Int,
    private val firstTop: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = bottom
        outRect.left = spacing
        outRect.right = spacing
        val index = parent.indexOfChild(view)
        if (index == 0 || index == 1) {
            outRect.top = firstTop
        }
    }
}