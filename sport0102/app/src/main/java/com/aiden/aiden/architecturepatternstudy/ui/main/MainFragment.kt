package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.RetrofitService
import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.api.retrofit
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment(private val marketName: String) : Fragment() {
    init {
    }

    private val marketKrw = "krw"
    private val marketBtc = "btc"
    private val marketEth = "eth"
    private val marketUsdt = "usdt"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getMarketList().enqueue(object : Callback<ArrayList<MarketModel>> {

            override fun onFailure(call: Call<ArrayList<MarketModel>>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>?, response: Response<ArrayList<MarketModel>>?) {
                if (null != response?.body()) {
                    var query: String = ""
                    response.body()?.filter { item -> item.market.startsWith(marketName, true) }
                        ?.map { item -> query += item.market + "," }
                    query = query.substring(0, query.length - 1)
                    Log.d("query", query)
                    retrofitService.getTickerInfo(query).enqueue(object : Callback<ArrayList<TickerModel>> {
                        override fun onFailure(call: Call<ArrayList<TickerModel>>, t: Throwable) {
                            Log.d("onFailure", t.toString())
                        }

                        override fun onResponse(
                            call: Call<ArrayList<TickerModel>>,
                            response: Response<ArrayList<TickerModel>>
                        ) {
                            response.body()?.let {
                                view.fragment_ticker_list_rv.layoutManager = LinearLayoutManager(context)
                                view.fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
                                    setData(it)
                                    notifyDataSetChanged()
                                }
                            }
                        }

                    })

                }
            }
        })
        return view
    }
}