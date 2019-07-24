package com.example.mystudy.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.adapter.TickerAdapter
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.network.TickerResponse
import com.example.mystudy.network.UpbitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_upbit.*
import org.jetbrains.anko.support.v4.ctx

class UpbitFragment(tabName: String) : Fragment() {

    private lateinit var tickerAdapter: TickerAdapter
    private val compositeDisposable = CompositeDisposable()
    var firstMarket = tabName
    private val repository: UpbitRepository by lazy { UpbitRepository(UpbitService.getInstance().upbitApi) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upbit, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<TickerResponse> = ArrayList()

        tickerAdapter = TickerAdapter(context!!, dataList)
        rv_korean.adapter = tickerAdapter
        rv_korean.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        repository.getMarket()
            .subscribe { it ->
                repository.getTicker(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.filter {
                            it.market.split("-")[0] == firstMarket
                        }
                    }
                    .subscribe({
                        displayData(it)
                    }, {
                    })
            }
    }

    private fun displayData(posts: List<TickerResponse>?) {
        val adapter = TickerAdapter(ctx, posts!!)
        rv_korean.adapter = adapter
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}

