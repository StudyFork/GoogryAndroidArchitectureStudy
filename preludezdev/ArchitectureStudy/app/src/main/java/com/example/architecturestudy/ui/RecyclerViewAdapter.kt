package com.example.architecturestudy.ui

import android.util.Log
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
        var marketList = StringBuffer()

        for (market in markets) {
            marketList.append(market + ",")
        }
        marketList.deleteCharAt(marketList.length - 1)

        //Ticker 가져오기
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
                    var list = response.body()
                    for (ticker in list!!) {
                        coins.add(ticker)
                        Log.d("test", ticker.market)
                        Log.d("test", String.format("%.2f", ticker.trade_price))
                        Log.d("test", ticker.toString())
                    }
                }
            })

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //코틀린 안드로이드 익스텐션을 사용해 레이아웃 내 뷰에 접근하려면
        //뷰홀더 내의 itemView를 거쳐야 한다.
        with(holder.itemView) {
            val item = coins[position]

            //뷰 id를 통해 직접 접근
            tvCoinName.text = item.market.split("-")[1]
            tvCurrPrice.text = String.format("%9.2f", item.trade_price)
            tvCoinCompare.text = String.format("%.2f", item.signed_change_rate * 100) + "%"
            tvCoinTotalTrade.text = String.format("%6.0f", (item.acc_trade_price_24h / 1000000)) + "M"

        }
    }

    override fun getItemCount(): Int = coins.size

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
    )


}