package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.customlistener.TabSelectedListener
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.data.upbit.source.local.UpbitLocalDataSource
import com.architecturestudy.data.upbit.source.local.UpbitRoom
import com.architecturestudy.data.upbit.source.remote.UpbitRemoteDataSource
import com.architecturestudy.data.upbit.source.remote.UpbitRetrofit
import com.architecturestudy.databinding.FragmentUpbitOutlineBinding
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable

class UpbitOutlineFragment : BaseFragment<FragmentUpbitOutlineBinding>(
    R.layout.fragment_upbit_outline
) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initViewPager()
    }

    override fun onDestroy() {
        CompositeDisposable().dispose()
        super.onDestroy()
    }

    private fun initBinding() {
        binding.vm = ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    UpbitViewModel(
                        UpbitRepository(
                            UpbitLocalDataSource(UpbitRoom.getDao(context)),
                            UpbitRemoteDataSource(UpbitRetrofit.service)
                        )
                    ) as T
            }
        )[UpbitViewModel::class.java]
    }

    private fun initViewPager() {
        binding.run {

            vpContents.run {
                adapter = fragmentManager?.let {
                    UpbitViewPagerAdapter(it)
                }
                addOnPageChangeListener(
                    TabLayout.TabLayoutOnPageChangeListener(tlMarketType)
                )
            }

            tlMarketType.run {
                MarketTypes.values().forEach {
                    addTab(newTab().setText(it.name))
                }
                addOnTabSelectedListener(object : TabSelectedListener() {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            vpContents.currentItem = it.position
                            vm?.currentTabPosition?.value = it.position
                        }
                    }
                })
            }

        }
    }

}