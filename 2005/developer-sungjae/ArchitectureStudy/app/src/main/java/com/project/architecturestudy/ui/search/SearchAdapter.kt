package com.project.architecturestudy.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.project.architecturestudy.base.BaseRecyclerViewAdapter
import com.project.architecturestudy.base.BaseViewHolder
import com.project.architecturestudy.data.model.MovieItem

class SearchAdapter<B : ViewDataBinding, ITEM : Any>(
    @LayoutRes private val itemLayoutRes: Int
) : BaseRecyclerViewAdapter<B, ITEM>(itemLayoutRes) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {
        return object : BaseViewHolder<B>(
            itemLayoutRes,
            parent = parent
        ) {}
    }
}

