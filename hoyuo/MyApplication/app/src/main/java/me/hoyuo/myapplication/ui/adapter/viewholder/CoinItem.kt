package me.hoyuo.myapplication.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import me.hoyuo.myapplication.R

class CoinItem(parent: ViewGroup) : AndroidExtensionsViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.coin_item, parent, false)
)