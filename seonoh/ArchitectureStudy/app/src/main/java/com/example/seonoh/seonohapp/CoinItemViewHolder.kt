package com.example.seonoh.seonohapp

import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.databinding.CoinItemBinding
import com.example.seonoh.seonohapp.model.UseCoinModel

class CoinItemViewHolder(private val binding : CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UseCoinModel) {
        binding.run {
            coinItem = data
        }
    }
}
