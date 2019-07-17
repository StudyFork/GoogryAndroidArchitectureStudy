package com.example.mystudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mystudy.fragment.BitcoinFragment
import com.example.mystudy.fragment.EthereumFragment
import com.example.mystudy.fragment.KoreanFragment
import com.example.mystudy.fragment.TetherFragment

class ViewPagerAdapter  (fm : FragmentManager, private val num_fragment:Int): FragmentStatePagerAdapter(fm){


    //Singleton Design Pattern: 기존에 생성되었던 객체를 재사용
    companion object{
        private var koreanFragment : KoreanFragment? = null
        private var bitcoinFragment : BitcoinFragment? = null
        private var ethereumFragment : EthereumFragment? = null
        private var tetherFragment : TetherFragment? = null
    }


    @Synchronized
    fun getKoreanFragment(): KoreanFragment {
        if (koreanFragment == null) koreanFragment = KoreanFragment()
        return koreanFragment!!
    }

    @Synchronized
    fun getBitcoinFragment(): BitcoinFragment {
        if (bitcoinFragment == null) bitcoinFragment = BitcoinFragment()
        return bitcoinFragment!!
    }

    @Synchronized
    fun getEthereumFragment(): EthereumFragment {
        if (ethereumFragment == null) ethereumFragment = EthereumFragment()
        return ethereumFragment!!
    }

    @Synchronized
    fun getTetherFragment(): TetherFragment {
        if (tetherFragment == null) tetherFragment = TetherFragment()
        return tetherFragment!!
    }

    override fun getItem(p0: Int): Fragment {
        return when(p0) {
            0 -> getKoreanFragment()
            1 -> getBitcoinFragment()
            2 -> getEthereumFragment()
            3 -> getTetherFragment()
            else -> null
        }!!
    }

    override fun getCount(): Int {
        return num_fragment
    }
}