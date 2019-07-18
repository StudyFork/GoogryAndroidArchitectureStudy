package com.example.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import kotlinx.android.synthetic.main.fragment_market.*

class EthFragment : Fragment() {

    private val ethRvAdapter = RecyclerViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_market, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰 어댑터와 레이아웃매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ethRvAdapter

        //마켓 데이터 불러오기
        ethRvAdapter.loadData(MainActivity.ETH_MARKETS)
    }
}