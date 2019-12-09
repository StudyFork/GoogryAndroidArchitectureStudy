package com.example.seonoh.seonohapp

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.di.MainViewModelFactory
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val pagerAdapter by lazy { TabPagerAdapter(supportFragmentManager) }


    @Inject
    lateinit var coinRepositoryImpl: CoinRepositoryImpl

    private val viewModelFactory by lazy {
        MainViewModelFactory(coinRepositoryImpl)
    }

    @Suppress("UNCHECKED_CAST")
    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            mainViewModel = viewModel
            coinViewPager.adapter = pagerAdapter
            tabLayout.setupWithViewPager(coinViewPager)
        }
    }

}
