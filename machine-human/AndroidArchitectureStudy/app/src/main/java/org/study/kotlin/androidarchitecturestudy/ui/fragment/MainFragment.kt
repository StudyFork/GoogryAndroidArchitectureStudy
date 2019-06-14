package org.study.kotlin.androidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.api.UPbitApi
import org.study.kotlin.androidarchitecturestudy.api.model.MarketModel
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {

    private val upbitApi: UpbitApi
    private val baseUrl = "https://api.upbit.com"
    var listData: ArrayList<TickerModel> = ArrayList()
    private var mainListAdapter: MainListAdapter? = null


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        restApi = retrofit.create(UPbitApi::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater.inflate(R.layout.fragment_main, container, false);
        view!!.recyclerview_mainfragment.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        view!!.recyclerview_mainfragment.layoutManager = layoutManager
        mainListAdapter = MainListAdapter(arrayListOf())
        val message = arguments!!.getString(EXTRA_MESSAGE)
        getMarketList(message)
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            val message = arguments!!.getString(EXTRA_MESSAGE)
            getMarketList(message)
        }
    }

    private fun getMarketList(key: String) {
        restApi.getMarket().enqueue(object : Callback<ArrayList<MarketModel>> {
            override fun onFailure(call: Call<ArrayList<MarketModel>>, t: Throwable) {
                Log.e("TAG onFailure", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>, response: Response<ArrayList<MarketModel>>) {
                response?.let {
                    val responseList = response.body()!!
                        .map { it.market }
                        .filter { it.substringBeforeLast("-") == key }
                    val coinList = responseList.joinToString()
                    getTickerList(coinList)
                }
            }
        })
    }

    private fun getTickerList(markets: String) {
        restApi.getTicker(markets).enqueue(object : Callback<ArrayList<TickerModel>> {
            override fun onFailure(call: Call<ArrayList<TickerModel>>, t: Throwable) {
                Log.e("TAG onFailure", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<TickerModel>>, response: Response<ArrayList<TickerModel>>) {
                listData = response?.body()!!
                mainListAdapter!!.setList(listData)
                recyclerview_mainfragment.adapter = mainListAdapter
                recyclerview_mainfragment.adapter!!.notifyDataSetChanged()
            }
        })
    }
    companion object {
        fun createInstance(message: String): MainFragment {
            val fragmentInstance = MainFragment()
            val bundle = Bundle(1)
            bundle.putString(EXTRA_MESSAGE, message)
            fragmentInstance.arguments = bundle
            return fragmentInstance
        }
    }
}
}


