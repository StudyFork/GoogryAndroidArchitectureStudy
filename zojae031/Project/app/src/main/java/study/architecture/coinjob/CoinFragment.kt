package study.architecture.coinjob

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import org.koin.androidx.viewmodel.ext.android.viewModel
import study.architecture.R
import study.architecture.base.BaseFragment
import study.architecture.base.BaseRecyclerViewAdapter
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.FragmentCoinBinding
import study.architecture.databinding.ItemTickerBinding


class CoinFragment : BaseFragment<FragmentCoinBinding>(R.layout.fragment_coin) {

    private val coinViewModel by viewModel<CoinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel =
                coinViewModel.also { it.getMarkets(arguments!!.getSerializable("idx") as FragIndex) }
            recyclerView.adapter = object :
                BaseRecyclerViewAdapter<ProcessingTicker, ItemTickerBinding>(
                    R.layout.item_ticker,
                    BR.pTicker
                ) {}

        }

    }


    override fun onResume() {
        super.onResume()
        coinViewModel.onResume()
    }


    enum class FragIndex {
        KRW, BTC, ETH, USDT
    }

}