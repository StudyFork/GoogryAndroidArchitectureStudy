package com.studyfirstproject.showcoin.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.base.BaseActivity
import com.studyfirstproject.databinding.ActivityMainBinding
import com.studyfirstproject.showcoin.CoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: CoinViewModel by viewModel()
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

        viewModel.errorMsg.observe(this, Observer { t ->
            Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
        })
    }
}
