package com.project.architecturestudy.ui.search

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.project.architecturestudy.base.BaseRecyclerViewAdapter

class SearchAdapter<B : ViewDataBinding, ITEM : Any>(
    @LayoutRes private val itemLayoutRes: Int
) : BaseRecyclerViewAdapter<B, ITEM>(itemLayoutRes)



