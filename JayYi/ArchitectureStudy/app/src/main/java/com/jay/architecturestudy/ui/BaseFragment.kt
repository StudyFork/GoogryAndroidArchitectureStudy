package com.jay.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.jay.architecturestudy.util.showToastMessage

abstract class BaseFragment(
    private val layoutId: Int
) : Fragment(), BaseContract.View {

    private val naverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    private val naverSearchLocalDataSource by lazy {
        NaverSearchLocalDataSourceImpl(activity!!)
    }

    val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl(
            naverSearchRemoteDataSource = naverSearchRemoteDataSource,
            naverSearchLocalDataSource = naverSearchLocalDataSource
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    abstract fun search(keyword: String)

    override fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}