package my.gong.studygong.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinMarketAdapter
import my.gong.studygong.base.BaseDialog
import my.gong.studygong.databinding.DialogCoinMarketBinding
import my.gong.studygong.viewmodel.CoinViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *          Coin Market 필터 다이얼로그
 *
 */
class CoinMarketDialog
    : BaseDialog<DialogCoinMarketBinding>(R.layout.dialog_coin_market) {

    private val coinViewModel: CoinViewModel by sharedViewModel()

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