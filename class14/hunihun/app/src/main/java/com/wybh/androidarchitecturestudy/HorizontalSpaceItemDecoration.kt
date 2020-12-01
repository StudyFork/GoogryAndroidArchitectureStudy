package com.wybh.androidarchitecturestudy

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val width: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = width
        outRect.right = width
    }
}