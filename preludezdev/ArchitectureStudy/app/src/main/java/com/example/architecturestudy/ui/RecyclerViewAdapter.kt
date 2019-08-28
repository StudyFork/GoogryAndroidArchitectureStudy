package com.example.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.databinding.ItemCoinBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val coins = mutableListOf<Coin>()
    private lateinit var binding: ItemCoinBinding

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
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemCoinBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(position)
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int = coins.size

    //뷰홀더가 item_coin 레이아웃 바인딩 객체를 들고있게 한다
    inner class MyViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            val currItem = coins[position]

            //각 레이아웃 내 뷰에 텍스트 및 색상 설정
            with(binding) {
                tvMarket.text = currItem.market
                tvTradePrice.text = currItem.tradePrice
                tvSignedChangedRate.text = currItem.signedChangeRate
                tvAccTradePriceH.text = currItem.accTradePriceH
                tvSignedChangedRate.setTextColor(currItem.coinColor)
            }
        }
    }

}