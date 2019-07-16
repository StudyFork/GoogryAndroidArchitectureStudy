package com.studyfirstproject.adapter

import androidx.annotation.LayoutRes
import com.studyfirstproject.base.BaseRecyclerView
import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.databinding.ItemCoinInfoBinding


class CoinRecyclerViewAdapter(
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int
) : BaseRecyclerView<ItemCoinInfoBinding, TickerModel>(layoutResId, bindingVariableId)