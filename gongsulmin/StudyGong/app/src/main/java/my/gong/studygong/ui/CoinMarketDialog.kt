package my.gong.studygong.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_coin_market.*
import my.gong.studygong.Injection
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinMarketAdapter
import my.gong.studygong.base.BaseDialog
import my.gong.studygong.databinding.DialogCoinMarketBinding
import my.gong.studygong.viewmodel.CoinViewModel

/**
 *          Coin Market 필터 다이얼로그
 *
 */
class CoinMarketDialog
    : BaseDialog<DialogCoinMarketBinding>(R.layout.dialog_coin_market) {

    private val coinViewModel: CoinViewModel by lazy {
        CoinViewModel(
            Injection.provideCoinRepository()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this@CoinMarketDialog
        viewDataBinding.coinViewModel = coinViewModel
        coinViewModel.loadBaseCurrency()

        coinViewModel.dismissCoinMarketDialog.observe(this, Observer {
            dismiss()
            (activity as CoinListActivity).onClickCoinMarketItem(it)
        })

        recyclerview_coin_market.adapter =
            CoinMarketAdapter(
                coinViewModel
            )
    }

}