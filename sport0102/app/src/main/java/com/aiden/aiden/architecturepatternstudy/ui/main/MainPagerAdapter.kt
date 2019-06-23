package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.retrofit
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val marketList = Market.values()

    private lateinit var presenter: MainPresenter
    private val upbitApi by lazy { retrofit.create(UpbitApi::class.java) }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val krwFragment = MainFragment(Market.KRW.marketName)
                presenter = MainPresenter(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), krwFragment
                )
                return krwFragment
            }
            1 -> {
                val btcFragment = MainFragment(Market.BTC.marketName)
                presenter = MainPresenter(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), btcFragment
                )
                return btcFragment
            }
            2 -> {
                val ethFragment = MainFragment(Market.ETH.marketName)
                presenter = MainPresenter(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), ethFragment
                )
                return ethFragment
            }
            3 -> {
                val usdtFragment = MainFragment(Market.USDT.marketName)
                presenter = MainPresenter(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), usdtFragment
                )
                return usdtFragment
            }
            else -> {
                val krwFragment = MainFragment(Market.KRW.marketName)
                presenter = MainPresenter(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), krwFragment
                )
                return krwFragment
            }
        }
    }

    override fun getCount(): Int = marketList.size
}
