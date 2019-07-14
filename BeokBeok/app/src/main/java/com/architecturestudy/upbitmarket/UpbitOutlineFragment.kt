package com.architecturestudy.upbitmarket

import android.os.Bundle
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.customlistener.TabSelectedListener
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.databinding.FragmentUpbitOutlineBinding
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable

class UpbitOutlineFragment : BaseFragment<FragmentUpbitOutlineBinding>(
    R.layout.fragment_upbit_outline
) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable().dispose()
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
                        }
                    }
                })
            }

        }
    }

}