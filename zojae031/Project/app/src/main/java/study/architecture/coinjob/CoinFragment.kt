package study.architecture.coinjob

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf
import study.architecture.R
import study.architecture.base.BaseFragment
import study.architecture.base.BaseRecyclerViewAdapter
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.FragmentCoinBinding
import study.architecture.databinding.ItemTickerBinding


class CoinFragment : BaseFragment<FragmentCoinBinding>(R.layout.fragment_coin), KoinComponent {

    private val coinViewModel by viewModel<CoinViewModel> {
        parametersOf(
            arguments!!.getSerializable(
                "idx"
            ) as FragIndex
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel =
                coinViewModel.also { it.getMarkets() }
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