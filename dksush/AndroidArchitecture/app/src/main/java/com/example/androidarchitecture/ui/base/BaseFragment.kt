package com.example.androidarchitecture.ui.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.androidarchitecture.common.StringConst
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceIml
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDs
import com.example.androidarchitecture.data.repository.NaverRepoImpl

abstract class BaseFragment<B : ViewDataBinding>(private val layoutId: Int) : Fragment() {


    protected lateinit var binding: B

    private val spm: SharedPreferences by lazy {
        requireContext().getSharedPreferences(StringConst.PREF_KEY, 0)
    }
    private val searchHistyDatabase: SearchHistDatabase by lazy {
        SearchHistDatabase.getInstance(requireContext())
    }



    private val naverSeacchLocalDataSource by lazy {
        NaverLocalDataSourceIml(spm, searchHistyDatabase)
    }

    private val naverSeacchRemoteDataSource by lazy {
        NaverRemoteDs()
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
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        return binding.root
    }

}