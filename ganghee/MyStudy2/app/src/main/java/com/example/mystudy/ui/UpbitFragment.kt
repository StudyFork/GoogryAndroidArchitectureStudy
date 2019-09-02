package com.example.mystudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.adapter.TickerAdapter
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.databinding.FragmentUpbitBinding
import org.jetbrains.anko.support.v4.toast

class UpbitFragment : Fragment(), UpbitContract.View {

    private lateinit var tickerAdapter: TickerAdapter
    override lateinit var presenter: UpbitContract.Presenter
    private lateinit var binding : FragmentUpbitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upbit,container,false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firstMarket = arguments?.getString(MARKET_NAME)

        recyclerViewSetup()
        presenter =
            UpbitPresenter(
                UpbitRepository.getInstance(),
                this@UpbitFragment
            )
        presenter.getTicker(firstMarket)
    }

    private fun recyclerViewSetup() {
        Log.d("recyclerViewSetup", "" )
        tickerAdapter = TickerAdapter()

        binding.rvTickers.apply {
            adapter = tickerAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun showUpbitTickerList(tickerList: List<FormatTickers>) {
        tickerAdapter.setData(tickerList)
    }

    override fun showFailedUpbitTickerList() {
        toast("tiker 데이터를 가져올 수 없습니다.").show()
    }

    companion object {
        const val MARKET_NAME = "market name"
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString(MARKET_NAME, marketName)
            Log.d("market3",""+marketName)
            fragment.arguments = bundle
            return fragment
        }
    }
}
