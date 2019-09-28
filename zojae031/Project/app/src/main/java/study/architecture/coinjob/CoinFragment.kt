package study.architecture.coinjob

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import study.architecture.base.BaseFragment
import study.architecture.base.BaseRecyclerViewAdapter
import study.architecture.Injection
import study.architecture.R
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.FragmentCoinBinding
import study.architecture.databinding.ItemTickerBinding


class CoinFragment : BaseFragment<FragmentCoinBinding>(R.layout.fragment_coin) {

    private lateinit var coinViewModel: CoinViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoinViewModel(
            arguments!!.getSerializable("idx") as FragIndex,
            Injection.getRepository(activity!!.applicationContext)
        ).also { coinViewModel = it }

        with(binding) {
            viewModel = coinViewModel
            recyclerView.adapter = object :
                BaseRecyclerViewAdapter<ProcessingTicker, ItemTickerBinding>(
                    R.layout.item_ticker,
                    BR.pTicker
                ) {}
        }

    }

    override fun onPause() {
        coinViewModel.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        coinViewModel.onResume()
    }


    enum class FragIndex {
        KRW, BTC, ETH, USDT
    }

}