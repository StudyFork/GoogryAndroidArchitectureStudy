package com.example.studyapplication.main.kin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.main.kin.adapter.KinAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.IConn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.KinList
import kotlinx.android.synthetic.main.fragment_kin.*

class KinFragment  : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val kinTitle = etQuery.text.toString()
            requestSearchKin(kinTitle)
        }
    }

    // 지식인 검색 요청
    private fun requestSearchKin(title : String) {
        Remote.get(ApiClient.getService().getKinList(title), object : IConn {
            override fun <T> success(result: T) {
                val kinList : KinList? = result as KinList
                kinList?.let {
                    setAdapter(it)
                }
            }

            override fun failed() {
            }
        })
    }

    // recyclerView 세팅
    private fun setAdapter(response : KinList) {
        recyclerView.adapter = KinAdapter(response.arrKinInfo)
    }

    companion object {
        fun newInstance() : KinFragment {
            return KinFragment()
        }
    }
}