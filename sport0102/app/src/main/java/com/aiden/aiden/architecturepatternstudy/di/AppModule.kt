package com.aiden.aiden.architecturepatternstudy.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.aiden.aiden.architecturepatternstudy.ui.main.MainSearchViewModel
import com.aiden.aiden.architecturepatternstudy.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getAppModule() = module {
    viewModel { (fragmentActivity: FragmentActivity) -> ViewModelProviders.of(fragmentActivity)[MainSearchViewModel::class.java] }
    viewModel { MainViewModel(get()) }
}