package com.studyfirstproject.showcoin.view

import android.os.Bundle
import com.studyfirstproject.R
import com.studyfirstproject.base.BaseActivity
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.databinding.ActivityMainBinding
import com.studyfirstproject.showcoin.CoinViewModel


class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by lazy { CoinViewModel(CoinRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.viewModel = viewModel
        viewModel.getMarketData()
    }
}
