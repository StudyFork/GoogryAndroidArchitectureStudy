package com.example.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.network.NetworkHelper
import kotlinx.android.synthetic.main.fragment_btc.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BTCFragment : Fragment() {
    var marketList = HashMap<String, String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_btc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        NetworkHelper
            .coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    var list: List<CoinMarketResponse>? = response.body()

                    var sb = StringBuffer()

                    for (num in 0..list!!.size - 1) {
                        var currMarketName = list[num].market
                        var arrMarketName = currMarketName.split("-")

                        marketList.put(arrMarketName[0], currMarketName)
                    }

                    marketList.forEach { (key, value) -> println("$key = $value") }

                    textViewBTC.text = sb.toString()
                }

                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    textViewBTC.text = t.toString()
                }
            })
    }

}