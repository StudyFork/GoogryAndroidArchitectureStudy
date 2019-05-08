package me.hoyuo.myapplication.ui.coinlist.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.util.viewholder.AndroidExtensionsViewHolder

class CoinItemViewHolder(parent: ViewGroup) : AndroidExtensionsViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.coin_item, parent, false)
)