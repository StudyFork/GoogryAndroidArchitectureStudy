package com.example.mystudy.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.*
import com.example.mystudy.adapter.RecyclerViewAdapter
import com.example.mystudy.network.RetrofitClient
import com.example.mystudy.network.TickerResponse
import com.example.mystudy.network.UpbitApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_ethereum.*
import org.jetbrains.anko.support.v4.ctx


class EthereumFragment : Fragment() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    internal lateinit var jsonApi: UpbitApi
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ethereum, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<TickerResponse> = ArrayList()

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(UpbitApi::class.java)

        recyclerViewAdapter = RecyclerViewAdapter(context!!, dataList)
        rv_ethereum.adapter = recyclerViewAdapter
        rv_ethereum.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        getMarketNameResponse()


    }

    private fun getMarketNameResponse() {

        compositeDisposable.add(
            jsonApi.getTickers("ETH-LTC, ETH-STRAT, ETH-XRP, ETH-ETC, ETH-OMG"+
            ", ETH-CVC, ETH-DGB, ETH-PAY, ETH-SC, ETH-SNT")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ marketList ->
                    displayData(marketList)
                }, {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                })
        )
    }

    private fun displayData(posts: List<TickerResponse>?) {
        val adapter = RecyclerViewAdapter(ctx, posts!!)
        rv_ethereum.adapter = adapter
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
