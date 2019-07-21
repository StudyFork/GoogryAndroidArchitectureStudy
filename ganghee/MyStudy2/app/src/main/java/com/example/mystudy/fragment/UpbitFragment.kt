package com.example.mystudy.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.adapter.RecyclerViewAdapter
import com.example.mystudy.network.RetrofitClient
import com.example.mystudy.network.TickerResponse
import com.example.mystudy.network.UpbitApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_upbit.*
import org.jetbrains.anko.support.v4.ctx

class UpbitFragment(tabName: String) : Fragment() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    internal lateinit var jsonApi: UpbitApi
    private val compositeDisposable = CompositeDisposable()
    var firstMarket = tabName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upbit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<TickerResponse> = ArrayList()

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(UpbitApi::class.java)

        recyclerViewAdapter = RecyclerViewAdapter(context!!, dataList)
        rv_korean.adapter = recyclerViewAdapter
        rv_korean.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        getMarketResponse()
    }

    private fun getMarketResponse() {

        compositeDisposable.add(
            jsonApi.getMarkets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map {
                        it.market
                    }.joinToString(",")
                }
                .subscribe({
                    jsonApi.getTickers(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map {
                            it.filter{
                                it.market.split("-")[0] == firstMarket
                            }
                        }
                        .subscribe({
                            displayData(it)
                        }, {
                        })
                }, {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    })
        )
    }

    private fun displayData(posts: List<TickerResponse>?) {
        val adapter = RecyclerViewAdapter(ctx, posts!!)
        rv_korean.adapter = adapter
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
