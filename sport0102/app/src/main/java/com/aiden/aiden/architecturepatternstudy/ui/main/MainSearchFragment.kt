package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentSearchMainBinding

class MainSearchFragment : BaseFragment<FragmentSearchMainBinding>(R.layout.fragment_search_main) {

    private lateinit var mainSearchVm: MainSearchViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        mainSearchVm =
            ViewModelProviders.of(activity!!)[MainSearchViewModel::class.java]
        binding {
            mainSearchViewModel = mainSearchVm
        }

    }

}

