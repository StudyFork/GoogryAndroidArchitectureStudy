package com.example.mystudy.ui.fragment


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
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.network.TickerResponse
import com.example.mystudy.network.UpbitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_upbit.*
import org.jetbrains.anko.support.v4.ctx

class UpbitFragment(tabName: String) : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private val firstMarket = tabName
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

        repository.getMarket()
            .subscribe { it ->
                repository.getTicker(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.filter {TickerResponse ->
                            TickerResponse.market.split("-")[0] == firstMarket
                        }
                    }
                    .subscribe({
                        displayData(it)
                    }, {
                    })
            }
    }

    private fun displayData(posts: List<TickerResponse>?) {
        posts?.let {
            val tickerList = mutableListOf<FormatTickers>()
            posts.forEach{
                tickerList.add(
                    it.toTicker(requireContext())
                )
            }
            rv_tickers.adapter = TickerAdapter(ctx, tickerList)
            rv_tickers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
