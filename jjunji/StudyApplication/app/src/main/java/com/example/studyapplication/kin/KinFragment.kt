package com.example.studyapplication.kin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyapplication.R
import com.example.studyapplication.kin.adapter.KinAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.IConn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.KinList
import kotlinx.android.synthetic.main.fragment_kin.*
import kotlinx.android.synthetic.main.fragment_kin.view.*

class KinFragment  : Fragment() {
    lateinit var mContext : Context

    companion object {
        fun newInstance() : KinFragment {
            return KinFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_kin, container, false)
        view.btnSearch.setOnClickListener(btnSearchClickListener())
        mContext = view.context

        return view
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
        recyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}