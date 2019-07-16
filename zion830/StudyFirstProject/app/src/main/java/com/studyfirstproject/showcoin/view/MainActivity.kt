package com.studyfirstproject.showcoin.view

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.base.BaseActivity
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.databinding.ActivityMainBinding
import com.studyfirstproject.showcoin.CoinViewModel


class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by lazy { CoinViewModel(CoinRepository()) }
    private val adapter = CoinRecyclerViewAdapter(R.layout.item_coin_info, BR.item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@MainActivity
        binding.rvMain.adapter = adapter
        viewModel.init()
    }
}
