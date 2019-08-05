package com.aiden.aiden.architecturepatternstudy.ui.main

import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentSearchMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainSearchFragment :
    BaseFragment<FragmentSearchMainBinding, MainSearchViewModel>(R.layout.fragment_search_main) {

    override val viewModel by sharedViewModel<MainSearchViewModel>()

}

