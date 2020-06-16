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

    private val movieList: MutableList<MovieItem> = mutableListOf()
    lateinit var onClick: ((MovieItem) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {
        return object : BaseViewHolder<B>(
            itemLayoutRes,
            parent = parent
        ) {}.apply {
            itemView.setOnClickListener {
                val item = movieList[adapterPosition]
                onClick.invoke(item)
            }
        }
    }
}

