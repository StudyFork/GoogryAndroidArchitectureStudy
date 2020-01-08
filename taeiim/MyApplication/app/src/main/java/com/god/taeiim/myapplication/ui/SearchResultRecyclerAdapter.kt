package com.god.taeiim.myapplication.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.base.BaseRecyclerAdapter
import com.god.taeiim.myapplication.base.BaseViewHolder

class SearchResultRecyclerAdapter<ITEM : Any, B : ViewDataBinding>(
    private val tab: Tabs,
    layout: Int,
    bindingVariableId: Int
) : BaseRecyclerAdapter<ITEM, B>(layout, bindingVariableId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {

        return BaseViewHolder(
            layoutResId,
            parent = parent,
            bindingVariableId = bindingVariableId
        )
    }

    override fun getItemViewType(position: Int): Int {
        return tab.ordinal
    }

}
