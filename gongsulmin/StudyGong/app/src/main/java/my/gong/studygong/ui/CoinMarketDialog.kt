package my.gong.studygong.ui

import android.os.Bundle
import androidx.lifecycle.Observer
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

    private lateinit var coinViewModel: CoinViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        coinViewModel = (activity as CoinListActivity).obtainViewModel()

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