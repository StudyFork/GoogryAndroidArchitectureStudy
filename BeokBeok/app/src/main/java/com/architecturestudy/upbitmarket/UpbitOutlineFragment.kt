package com.architecturestudy.upbitmarket

import android.os.Bundle
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.customlistener.TabSelectedListener
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.databinding.FragmentUpbitOutlineBinding
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class UpbitOutlineFragment : BaseFragment<FragmentUpbitOutlineBinding, UpbitViewModel>(
    R.layout.fragment_upbit_outline
) {
    override val viewModel by viewModel<UpbitViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel
        initViewPager()
    }

    private fun initViewPager() {
        binding.run {

            vpContents.run {
                offscreenPageLimit = 3
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