package com.example.mystudy.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.databinding.RvItemListBinding

class UpbitHolder(
    private val binding: RvItemListBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var rvBinding: RvItemListBinding = binding

    fun bind(ticker: FormatTickers) {
        rvBinding.item = ticker
    }
}