package com.example.studyapplication.main.kin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.main.kin.adapter.KinAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.data.model.SearchKinResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import kotlinx.android.synthetic.main.fragment_kin.*

class KinFragment  : Fragment() {
    lateinit var kinAdapter: KinAdapter
    private val repository : NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
        kinAdapter = KinAdapter()
        recyclerView.adapter = kinAdapter
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
        repository.getKinList(title, object : Conn {
            override fun <T> success(result: T) {
                val searchData : SearchKinResult? = result as SearchKinResult
                searchData?.let {
                    kinAdapter.resetItem(searchData.arrKinInfo)
                }
            }

            override fun failed() {

            }

        })
    }

    companion object {
        fun newInstance() : KinFragment {
            return KinFragment()
        }
    }
}