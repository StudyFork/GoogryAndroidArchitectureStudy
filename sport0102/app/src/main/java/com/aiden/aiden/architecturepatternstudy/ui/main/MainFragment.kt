package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.api.retrofit
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment(private val market: Market) : Fragment() {

    init {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val retrofitService = retrofit.create(UpbitApi::class.java)
        retrofitService.getMarketList().enqueue(object : Callback<ArrayList<MarketModel>> {

            override fun onFailure(call: Call<ArrayList<MarketModel>>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>?, response: Response<ArrayList<MarketModel>>?) {
                response?.body()?.let {
                    var query = ""
                    response.body()?.filter { item -> item.market.startsWith(market.marketName, true) }
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
                                with(view) {
                                    fragment_ticker_list_rv.layoutManager = LinearLayoutManager(context)
                                    fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
                                        setData(it)
                                        notifyDataSetChanged()
                                    }
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