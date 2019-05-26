package my.gong.studygong.mvp.view

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_coin_detail.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.mvp.Injection
import my.gong.studygong.mvp.adapter.CoinAdapter
import my.gong.studygong.mvp.base.BaseDialog
import my.gong.studygong.mvp.presenter.coindetail.CoinDetailContract
import my.gong.studygong.mvp.presenter.coindetail.CoinDetailPresenter
import my.gong.studygong.mvp.view.CoinListActivity.Companion.COIN_DETAIL

/**
 *          Coin 검색 다이얼로그
 *
 */
class CoinDetailDialog
    : BaseDialog(R.layout.dialog_coin_detail), CoinDetailContract.View {

    private val presenter: CoinDetailContract.Presenter by lazy {
        CoinDetailPresenter(
            Injection.provideCoinRepository(),
            this
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerview_coin_detail.adapter = CoinAdapter()

        arguments?.getString(COIN_DETAIL)?.let {
            presenter.populateCoinData(it)
        }
    }

    override fun showCoinMarket(coinDetail: List<Ticker>) {
        (recyclerview_coin_detail.adapter as CoinAdapter).refreshData(coinDetail)
    }

    override fun errorCoinDetail(errorMsg: String) {
        txt_coin_detail_no_data.run {
            text = errorMsg
            visibility = View.VISIBLE
        }
    }
}