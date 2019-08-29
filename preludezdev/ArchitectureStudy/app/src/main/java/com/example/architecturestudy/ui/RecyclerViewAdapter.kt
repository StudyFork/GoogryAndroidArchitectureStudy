package com.example.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseViewHolder
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.databinding.ItemCoinBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val coins = mutableListOf<Coin>()

    fun clearData() {
        coins.clear()
        notifyDataSetChanged()
    }

    fun setData(data: List<Coin>) {
        coins.clear()
        coins.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(coins[position])
    }

    override fun getItemCount(): Int = coins.size

    inner class MyViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemCoinBinding>(R.layout.item_coin, parent) {

        fun bindView(item: Coin) {
            //각 레이아웃 내 뷰에 텍스트 및 색상 설정
            with(binding) {
                tvMarket.text = item.market
                tvTradePrice.text = item.tradePrice
                tvSignedChangedRate.text = item.signedChangeRate
                tvAccTradePriceH.text = item.accTradePriceH
                tvSignedChangedRate.setTextColor(item.coinColor)

                executePendingBindings()
            }
        }
    }

}