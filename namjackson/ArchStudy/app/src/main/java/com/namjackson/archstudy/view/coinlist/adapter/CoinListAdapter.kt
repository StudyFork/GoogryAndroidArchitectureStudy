package com.namjackson.archstudy.view.coinlist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.namjackson.archstudy.R
import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.databinding.ItemCoinListBinding


class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private val coinList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding = DataBindingUtil.inflate<ItemCoinListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_coin_list,
            parent,
            false
        )
        return CoinListViewHolder(binding)
    }

    fun setList(list: List<Ticker>) {
        coinList.clear()
        coinList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, i: Int) {
        holder.onBind(coinList.get(i))
    }

    class CoinListViewHolder(val binding: ItemCoinListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(ticker: Ticker) {
            binding.let {
                it.market.text = ticker.name
                it.tradePrice.text = ticker.tradePrice
                it.beforePrice.text = ticker.changeRate
                it.volume.text = ticker.tradeVolume
                if (ticker.change.equals("RISE"))
                    it.beforePrice.setTextColor(Color.RED)
                else if (ticker.change.equals("FALL"))
                    it.beforePrice.setTextColor(Color.BLUE)
                else
                    it.beforePrice.setTextColor(Color.BLACK)
            }
        }
    }

}