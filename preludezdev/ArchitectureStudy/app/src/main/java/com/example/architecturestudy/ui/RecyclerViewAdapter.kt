package com.example.architecturestudy.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.util.Util
import kotlinx.android.synthetic.main.item_coin.view.*


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val coins = mutableListOf<CoinTickerResponse>()

    fun clearData() {
        coins.clear()
    }

    fun addData(data: CoinTickerResponse) {
        coins.add(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //뷰홀더 객체의 프로퍼티를 binding 해준다.
        with(holder) {
            val currItem = coins[position]

            //각 레이아웃 내 뷰에 텍스트 설정
            tvCoinName.text = currItem.market.split("-")[1]
            tvCoinCompare.text = String.format("%.2f", currItem.signedChangeRate * 100) + "%"

            if (currItem.signedChangeRate > 0) {
                tvCoinCompare.setTextColor(Color.RED)
            } else {
                tvCoinCompare.setTextColor(Color.BLUE)
            }

            //데이터의 크기에 따라 ui를 다르게 보여주게끔 설정
            when {
                currItem.tradePrice > 1000 ->
                    tvCurrPrice.text = Util.convertBigNumberToStdString(currItem.tradePrice.toInt())
                currItem.tradePrice > 2 ->
                    tvCurrPrice.text = String.format("%.2f", currItem.tradePrice)
                else ->
                    tvCurrPrice.text = String.format("%.8f", currItem.tradePrice)
            }

            when {
                currItem.accTradePriceH > 10000000 -> {
                    tvCoinTotalTrade.text =
                        Util.convertBigNumberToStdString((currItem.accTradePriceH / 1000000).toInt()) + "M"
                }

                currItem.accTradePriceH > 100000 ->
                    tvCoinTotalTrade.text =
                        Util.convertBigNumberToStdString(currItem.accTradePriceH.toInt() / 1000) + "k"

                currItem.accTradePriceH > 1000 ->
                    tvCoinTotalTrade.text =
                        Util.convertBigNumberToStdString(currItem.accTradePriceH.toInt())

                else ->
                    tvCoinTotalTrade.text = String.format("%.3f", currItem.accTradePriceH)
            }
        }
    }

    override fun getItemCount(): Int = coins.size

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
    ) {
        // 각 뷰의 인스턴스를 저장하는 프로퍼티를 추가합니다.
        // 생성자가 호출되는 시점에 뷰의 인스턴스가 할당됩니다.
        val tvCoinName = itemView.tvCoinName
        val tvCoinCompare = itemView.tvCoinCompare
        val tvCurrPrice = itemView.tvCurrPrice
        val tvCoinTotalTrade = itemView.tvCoinTotalTrade
    }

}