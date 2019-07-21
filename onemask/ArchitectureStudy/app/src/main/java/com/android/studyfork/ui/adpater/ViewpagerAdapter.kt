package com.android.studyfork.ui.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.studyfork.repository.remote.model.MarketAllResponse
import com.android.studyfork.ui.*
import timber.log.Timber

class ViewpagerAdapter(fragmentMnager : FragmentManager,
                       val allMarketList : List<MarketAllResponse>,
                       val tabCount : Int ) : FragmentPagerAdapter(fragmentMnager) {

    private var krwList : ArrayList<String> = ArrayList()
    private var btcList: ArrayList<String> = ArrayList()
    private var ethList: ArrayList<String> = ArrayList()
    private var usdtList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        Timber.d("market ${allMarketList.size}")
        if(krwList.isEmpty() && btcList.isEmpty())
            for (response in allMarketList)
                when (response.market.split("-")[0]){
                    "KRW" -> krwList.add(response.market)
                    "BTC" -> btcList.add(response.market)
                    "ETH" -> ethList.add(response.market)
                    "USDT" -> usdtList.add(response.market)
                }
        when(position){
            0-> return newTikcerListFragment(krwList)
            1-> return newTikcerListFragment(btcList)
            2-> return newTikcerListFragment(ethList)
            3-> return newTikcerListFragment(usdtList)
            else -> error("e")
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
