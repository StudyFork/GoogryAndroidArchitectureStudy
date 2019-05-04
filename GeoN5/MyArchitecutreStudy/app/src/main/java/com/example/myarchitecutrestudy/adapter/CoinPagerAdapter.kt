package com.example.myarchitecutrestudy.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.myarchitecutrestudy.fragment.CoinFragment
import com.example.myarchitecutrestudy.model.MarketAll

class CoinPagerAdapter(fm: FragmentManager?, private val tabCount:Int, marketList:List<MarketAll>) : FragmentPagerAdapter(fm) {

    private var krwList:ArrayList<String> = ArrayList()
    private var btcList:ArrayList<String> = ArrayList()
    private var ethList:ArrayList<String> = ArrayList()
    private var usdtList:ArrayList<String> = ArrayList()

    init {
        marketList.forEach {
            when(it.market.substring(0,3)){
                "KRW" -> krwList.add(it.market)
                "BTC" -> btcList.add(it.market)
                "ETH" -> ethList.add(it.market)
                "USD" -> usdtList.add(it.market)
            }
        }
    }
//    이런식으로도 한번 해봤는데 이부분에서는
//    메인에서 setList가 호출되기전에 뷰페이저가 먼저 getItem작업을 수행해서
//    빈 리스트가 들어가는 현상이 발생해서 이 부분은 원래와 같이 두었습니다.
//    fun setList(list:List<MarketAll>){
//        list.forEach {
//            when(it.market.substring(0,3)){
//                "KRW" -> krwList.add(it.market)
//                "BTC" -> btcList.add(it.market)
//                "ETH" -> ethList.add(it.market)
//                "USD" -> usdtList.add(it.market)
//            }
//        }
//    }

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> {
                CoinFragment.newInstance(krwList)
            }
            1 ->{
                CoinFragment.newInstance(btcList)
            }
            2 -> {
                CoinFragment.newInstance(ethList)
            }
            3->{
                CoinFragment.newInstance(usdtList)
            }
            else -> null
        }

    }

    override fun getCount(): Int {
        return tabCount
    }

}