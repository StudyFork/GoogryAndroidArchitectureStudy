package com.example.seonoh.seonohapp

import android.os.Bundle
import com.example.seonoh.seonohapp.contract.CoinMainContract
import com.example.seonoh.seonohapp.model.Market

class MainActivity : BaseActivity(), CoinMainContract.View {

    override val presenter: CoinMainContract.Presenter by lazy { CoinMainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        presenter.loadData()
    }

    override fun setData(data: List<Market>) = pagerAdapter.setData(refineData(data))


}
