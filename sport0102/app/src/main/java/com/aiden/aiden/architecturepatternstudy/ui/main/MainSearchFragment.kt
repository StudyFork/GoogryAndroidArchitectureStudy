package com.aiden.aiden.architecturepatternstudy.ui.main

import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentSearchMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainSearchFragment :
    BaseFragment<FragmentSearchMainBinding, MainSearchViewModel>(R.layout.fragment_search_main) {

    override val viewModel =
        lazy { getViewModel<MainSearchViewModel>(parameters = { parametersOf(activity) }) }

}

