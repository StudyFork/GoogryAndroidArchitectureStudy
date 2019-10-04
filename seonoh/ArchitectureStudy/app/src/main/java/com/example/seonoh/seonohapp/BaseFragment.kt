package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.coin_fragment.*

abstract class BaseFragment : Fragment(){

    lateinit var mAdapter: CoinAdapter
    var marketName: String? = null

    fun initView() {
        mAdapter = CoinAdapter()
        krwRecyclerView.apply {
            adapter = mAdapter
        }
    }
}