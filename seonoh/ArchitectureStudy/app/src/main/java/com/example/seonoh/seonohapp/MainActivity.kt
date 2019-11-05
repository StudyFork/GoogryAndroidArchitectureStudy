package com.example.seonoh.seonohapp

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val pagerAdapter by lazy { TabPagerAdapter(supportFragmentManager) }

    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(itemClass: Class<T>): T {
                return MainViewModel(
                    CoinRepositoryImpl()
                ) as T
            }
        }).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
            coinViewPager.run {
                adapter = pagerAdapter
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            }
            tabLayout.run {
                addTab(tabLayout.newTab().setText("KRW"))
                addTab(tabLayout.newTab().setText("BTC"))
                addTab(tabLayout.newTab().setText("ETH"))
                addTab(tabLayout.newTab().setText("USDT"))
                addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
            }
        }
    }

}
