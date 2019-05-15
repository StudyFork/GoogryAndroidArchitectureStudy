package dev.daeyeon.gaasproject.ui.ticker

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.response.ResponseCode
import dev.daeyeon.gaasproject.data.source.UpbitRepository
import dev.daeyeon.gaasproject.databinding.FragmentTickerBinding
import dev.daeyeon.gaasproject.network.NetworkManager
import org.jetbrains.anko.toast

class TickerFragment : Fragment(), TickerContract.View {

    override lateinit var presenter: TickerContract.Presenter
    private lateinit var binding: FragmentTickerBinding
    private lateinit var tickerAdapter: TickerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ticker, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tickerAdapter = TickerAdapter()
        binding.rvTicker.adapter = tickerAdapter

        presenter = TickerPresenter(
            view = this,
            upbitRepository = UpbitRepository(upbitApi = NetworkManager.instance)
        )

        swipeInit()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onDestroyView() {
        presenter.cancelApi()
        super.onDestroyView()
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
            setOnRefreshListener { presenter.loadUpbitTicker() }
        }
    }

    override fun showProgress() {
        binding.srlTicker.isRefreshing = true
    }

    override fun hideProgress() {
        binding.srlTicker.isRefreshing = false
    }

    override fun replaceTickerList(tickerList: List<Ticker>) {
        tickerAdapter.replaceList(tickerList)
    }

    override fun toastTickerFailMsg(msg: String) {
        when (msg) {
            ResponseCode.CODE_NULL_SUCCESS,
            ResponseCode.CODE_NULL_FAIL_MSG -> activity!!.toast(R.string.all_network_error)
            ResponseCode.CODE_EMPTY_SUCCESS -> activity!!.toast(R.string.ticker_fragment_empty)
            else -> activity!!.toast(msg)
        }
    }

    companion object {
        fun newInstance(): TickerFragment {
            return TickerFragment()
        }
    }
}
