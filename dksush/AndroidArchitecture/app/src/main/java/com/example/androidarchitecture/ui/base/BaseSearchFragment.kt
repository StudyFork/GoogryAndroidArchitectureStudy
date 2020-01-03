package com.example.androidarchitecture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceIml
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDs
import com.example.androidarchitecture.data.repository.NaverRepoImpl

abstract class BaseSearchFragment(private val layoutId: Int) : Fragment() {


    private val naverSeacchRemoteDataSource by lazy {
        NaverRemoteDs()
    }

    private val naverSeacchLocalDataSource by lazy {
        NaverLocalDataSourceIml(activity!!)
    }


    val naverSearchRepository by lazy {
        NaverRepoImpl(
            naverRemoteDs = naverSeacchRemoteDataSource,
            naverLocalDs = naverSeacchLocalDataSource
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutId, container, false)
    }

}