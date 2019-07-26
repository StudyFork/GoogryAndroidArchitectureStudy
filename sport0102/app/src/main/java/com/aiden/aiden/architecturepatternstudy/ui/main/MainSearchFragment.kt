package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.Retrofit
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentSearchMainBinding

class MainSearchFragment : BaseFragment<FragmentSearchMainBinding>(R.layout.fragment_search_main) {

    private val upbitApi by lazy { Retrofit.retrofit.create(UpbitApi::class.java) }

    private lateinit var mainSearchVm: MainSearchViewModel

    private lateinit var marketName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        mainSearchVm =
            ViewModelProviders.of(context as MainActivity)[MainSearchViewModel::class.java]

        binding {
            mainSearchViewModel = mainSearchVm
        }

    }

}

