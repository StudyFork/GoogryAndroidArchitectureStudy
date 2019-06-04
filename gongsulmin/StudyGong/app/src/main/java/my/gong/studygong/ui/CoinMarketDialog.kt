package my.gong.studygong.ui

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_coin_market.*
import my.gong.studygong.Injection
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinMarketAdapter
import my.gong.studygong.base.BaseDialog
import my.gong.studygong.viewmodel.CoinViewModel

/**
 *          Coin Market 필터 다이얼로그
 *
 */
class CoinMarketDialog
    : BaseDialog(R.layout.dialog_coin_market) {

    lateinit var dialogCallBackListener: (String) -> Unit

    private val coinViewModel: CoinViewModel by lazy {
        CoinViewModel(
            Injection.provideCoinRepository()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerview_coin_market.adapter =
            CoinMarketAdapter(
                clickCoinMarketListener = {
                    dialogCallBackListener.invoke(it)
                    dismiss()
                }
        )
        coinViewModel.loadBaseCurrency()

        coinViewModel.baseCurrencyLoadedListener = {
            (recyclerview_coin_market.adapter as CoinMarketAdapter).refreshData(it)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        dialogCallBackListener = {
            (activity as CoinListActivity).onClickCoinMarketItem(it)
        }
    }
}