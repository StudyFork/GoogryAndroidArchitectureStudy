package my.gong.studygong.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_coin_detail.*
import my.gong.studygong.Injection
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinAdapter
import my.gong.studygong.base.BaseDialog
import my.gong.studygong.ui.CoinListActivity.Companion.COIN_DETAIL
import my.gong.studygong.viewmodel.CoinViewModel

/**
 *          Coin 검색 다이얼로그
 *
 */
class CoinSearchDialog
    : BaseDialog(R.layout.dialog_coin_detail) {

    private val coinViewModel: CoinViewModel by lazy {
        CoinViewModel(
            Injection.provideCoinRepository()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerview_coin_detail.adapter = CoinAdapter()

        arguments?.getString(COIN_DETAIL)?.let {
            coinViewModel.loadTickerSearchResult(it)
        }

        coinViewModel.searchTickerLoadedListener = {
            (recyclerview_coin_detail.adapter as CoinAdapter).refreshData(it)
        }

        coinViewModel.errorLoadedListener = {
            txt_coin_detail_no_data.visibility = View.VISIBLE
            txt_coin_detail_no_data.text = it
        }
    }
}