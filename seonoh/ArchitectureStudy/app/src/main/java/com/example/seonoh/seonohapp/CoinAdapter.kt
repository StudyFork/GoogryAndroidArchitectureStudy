package com.example.seonoh.seonohapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

class CoinAdapter : RecyclerView.Adapter<CoinItemViewHolder>() {

    private val coinData = mutableListOf<UseCoinModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {

        return CoinItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.coin_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = coinData.size

    fun setCoinData(data: List<UseCoinModel>?) {
        if (!data.isNullOrEmpty())
        {
            coinData.run {
                clear()
                addAll(data)
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(coinData[position])
    }
}

