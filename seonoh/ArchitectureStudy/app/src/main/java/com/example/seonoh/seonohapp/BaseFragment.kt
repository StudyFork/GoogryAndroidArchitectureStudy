package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.coin_fragment.*

abstract class BaseFragment : Fragment(){

    protected lateinit var fragmentAdapter: CoinAdapter
    protected var marketName: String? = null

    fun initView() {
        fragmentAdapter = CoinAdapter()
        krwRecyclerView.apply {
            adapter = fragmentAdapter
        }
    }
}