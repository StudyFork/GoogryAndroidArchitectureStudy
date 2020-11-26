package com.wybh.androidarchitecturestudy

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(private val verticalSpaceWidth: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = verticalSpaceWidth
        outRect.right = verticalSpaceWidth
    }
}