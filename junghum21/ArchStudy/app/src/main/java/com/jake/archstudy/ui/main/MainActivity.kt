package com.jake.archstudy.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseActivity
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.ActivityMainBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.ui.tickers.TickersFragment
import com.jake.archstudy.util.ResourceProviderImpl

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel by lazy {
        @Suppress("UNCHECKED_CAST")
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService())),
                    ResourceProviderImpl(applicationContext)
                ) as T
            }
        }).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabLayout()
        observeMarkets()
        binding.run {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
    }

    private fun observeMarkets() {
        viewModel.markets.observe(
            this,
            object : Observer<List<Market>?> {
                override fun onChanged(t: List<Market>?) {
                    setViewPager(t ?: return)
                }
            }
        )
    }

    private fun setViewPager(markets: List<Market>) {
        binding.vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) =
                TickersFragment.newInstance(markets[position].markets)

            override fun getCount() = markets.size

            override fun getPageTitle(position: Int) = markets[position].title
        }
    }

    private fun initTabLayout() {
        binding.tlMarket.setupWithViewPager(binding.vpContent)
    }

}