package com.example.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinVo
import kotlinx.android.synthetic.main.item_coin.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val coins = listOf(
        CoinVo(213.0, "123"),
        CoinVo(213.0, "123"),
        CoinVo(213.0, "123"),
        CoinVo(213.0, "123"),
        CoinVo(213.0, "123")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //코틀린 안드로이드 익스텐션을 사용해 레이아웃 내 뷰에 접근하려면
        //뷰홀더 내의 itemView를 거쳐야 한다.
        with(holder.itemView) {
            val item = coins[position]

            //뷰 id를 통해 직접 접근
            tvCoinName.text = item.market
            tvCurrPrice.text = item.tradePrice.toString()
            tvCoinCompare.text = item.changeRate.toString()
            tvCoinTotalTrade.text = item.tradeVolume.toString()

        }
    }

    override fun getItemCount(): Int = coins.size

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
    )


}