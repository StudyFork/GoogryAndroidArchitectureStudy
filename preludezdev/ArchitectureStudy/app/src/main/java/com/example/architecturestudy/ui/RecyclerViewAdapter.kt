package com.example.architecturestudy.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.network.NetworkHelper
import kotlinx.android.synthetic.main.item_coin.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val coins = mutableListOf<CoinTickerResponse>()

    fun loadData(markets: HashSet<String>) {
        coins.clear()

        var marketList = StringBuffer()

        for (market in markets) {
            marketList.append(market + ",")
        }
        marketList.deleteCharAt(marketList.length - 1)

        //Ticker 정보들 가져오기
        NetworkHelper
            .coinApiService
            .getCurrTicker(marketList.toString())
            .enqueue(object : Callback<List<CoinTickerResponse>> {
                override fun onFailure(call: Call<List<CoinTickerResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CoinTickerResponse>>,
                    response: Response<List<CoinTickerResponse>>
                ) {
                    if (response.isSuccessful) {
                        var list = response.body()
                        for (ticker in list!!) {
                            coins.add(ticker)
                        }

                        notifyDataSetChanged()
                    }
                }
            })
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
            if (currItem.tradePrice > 1) {
                tvCurrPrice.text = String.format("%9.2f", currItem.tradePrice)
            } else {
                tvCurrPrice.text = String.format("%.8f", currItem.tradePrice)
            }

            when {
                currItem.accTradePriceH > 10000000 ->
                    tvCoinTotalTrade.text = String.format("%6.0f", (currItem.accTradePriceH / 1000000)) + "M"
                currItem.accTradePriceH > 10000 ->
                    tvCoinTotalTrade.text = String.format("%6.0f", (currItem.accTradePriceH / 1000)) + "k"
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