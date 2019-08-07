package com.example.mystudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_upbit.*
import org.jetbrains.anko.support.v4.toast

class UpbitFragment(tabName: String) : Fragment(), UpbitContract.View {


    private val firstMarket = tabName
    private lateinit var tickerAdapter: TickerAdapter
    override lateinit var presenter: UpbitContract.Presenter

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
        recyclerViewSetup()
        presenter =
            UpbitPresenter(
                UpbitRepository.getInstance(),
                this@UpbitFragment
            )
        presenter.getTicker(firstMarket)
    }

    private fun recyclerViewSetup() {
        Log.d("recyclerViewSetup", "" + firstMarket)
        tickerAdapter = TickerAdapter()

        rv_tickers.apply {
            adapter = tickerAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun showSuccessUpbitTickerList(tickerList: MutableList<FormatTickers>) {
        tickerAdapter.setData(tickerList)

    }

    override fun showFailedUpbitTickerList() {
        toast("$firstMarket tiker 데이터를 가져올 수 없습니다.").show()
    }
}
