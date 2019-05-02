package com.namjackson.archstudy.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.databinding.ItemCoinListBinding
import android.view.LayoutInflater
import com.namjackson.archstudy.R
import android.graphics.Color


class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private val coinList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        var binding = DataBindingUtil.inflate<ItemCoinListBinding>(
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
                it.market.text = ticker.market.split("-").get(1)
                it.tradePrice.text =
                    if (ticker.tradePrice.toString().length > 4) ticker.tradePrice.toInt().toString() else ticker.tradePrice.toString()
                it.beforePrice.text = (ticker.signedChangeRate * 100).toFloat().toString() + "%"
                it.volume.text =
                    if (ticker.accTradePrice24h.toLong() > 1000000L) (ticker.accTradePrice24h / 1000000L).toInt().toString() + "M" else ticker.accTradePrice24h.toInt().toString()
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