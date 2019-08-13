package com.test.androidarchitecture


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CoinFragment : Fragment() {
    companion object {

        fun getInstance(coinFilterList: ArrayList<String>): CoinFragment {
            val args: Bundle = Bundle();
            args.putStringArrayList("coinFilterList", coinFilterList)
            val fragment = CoinFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

}
