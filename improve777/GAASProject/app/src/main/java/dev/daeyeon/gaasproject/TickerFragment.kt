package dev.daesin.gaasdy

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.data.source.UpbitRepository
import dev.daeyeon.gaasproject.databinding.FragmentTickerBinding

class TickerFragment : Fragment() {

    private lateinit var binding: FragmentTickerBinding
    private var tickerAdapter: TickerAdapter? = null
    private val repository = UpbitRepository()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ticker, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tickerAdapter = TickerAdapter()

        binding.rvTicker.apply {
            this.adapter = tickerAdapter
        }

        swipeInit()
        getTicker()
    }

    /**
     * swipeRefreshLayout 버튼 색, 이벤트 설정
     */
    private fun swipeInit() {
        binding.srlTicker.apply {
            setColorSchemeResources(
                    android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
            )
            setOnRefreshListener { getTicker() }
        }
    }

    /**
     * ticker 통신
     */
    private fun getTicker() {
        binding.srlTicker.run {
            isRefreshing = true
            repository.getTicker(
                    success = {
                        isRefreshing = false
                        tickerAdapter?.clearList()
                        tickerAdapter?.addList(it)
                    },
                    fail = {
                        isRefreshing = false
                        Toast.makeText(activity!!, it, Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }

    companion object {
        const val TAG = "TickerFragment"
        fun newInstance(): TickerFragment {
            return TickerFragment()
        }
    }
}
