package my.gong.studygong.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import my.gong.studygong.Injection
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinMarketAdapter
import my.gong.studygong.base.BaseDialog
import my.gong.studygong.databinding.DialogCoinMarketBinding
import my.gong.studygong.viewmodel.CoinViewModel
import my.gong.studygong.viewmodel.ViewModelFactoryImpl

/**
 *          Coin Market 필터 다이얼로그
 *
 */
class CoinMarketDialog
    : BaseDialog<DialogCoinMarketBinding>(R.layout.dialog_coin_market) {

    private val coinViewModel: CoinViewModel by lazy {
        ViewModelProviders.of(activity as CoinListActivity, ViewModelFactoryImpl(Injection.provideCoinRepository()))
            .get(CoinViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.coinViewModel = coinViewModel

        coinViewModel.loadBaseCurrency()

        coinViewModel.dismissCoinMarketDialog.observe(this, Observer {
            dismiss()
        })

        viewDataBinding.recyclerviewCoinMarket.adapter =
            CoinMarketAdapter(
                coinViewModel
            )
    }

}