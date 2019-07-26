package com.example.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.Coin
import kotlinx.android.synthetic.main.item_coin.view.*


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val coins = mutableListOf<Coin>()

    fun clearData() {
        coins.clear()
        notifyDataSetChanged()
    }

    fun addData(data: Coin) {
        coins.add(data)
        notifyItemInserted(coins.lastIndex)
    }

    fun setData(data: List<Coin>) {
        coins.clear()
        coins.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = coins.size

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
    ) {
        // 각 뷰의 인스턴스를 저장하는 프로퍼티를 추가합니다.
        // 생성자가 호출되는 시점에 뷰의 인스턴스가 할당됩니다.
        private val tvMarket = itemView.tv_market
        private val tvTradePrice = itemView.tv_trade_price
        private val tvSignedChangedRate = itemView.tv_signed_changed_rate
        private val tvAccTradePriceH = itemView.tv_acc_trade_price_h

        fun bindView(position: Int) {
            //뷰홀더 객체의 프로퍼티를 binding 해준다.
            val currItem = coins[position]

            //각 레이아웃 내 뷰에 텍스트 및 색상 설정
            tvMarket.text = currItem.market
            tvTradePrice.text = currItem.tradePrice
            tvSignedChangedRate.text = currItem.signedChangeRate
            tvAccTradePriceH.text = currItem.accTradePriceH
            tvSignedChangedRate.setTextColor(currItem.coinColor)
        }
    }
}