package com.egiwon.architecturestudy.di

import com.egiwon.architecturestudy.ui.Tab
import com.egiwon.architecturestudy.ui.tabs.ContentsViewModel
import com.egiwon.architecturestudy.ui.tabs.bottomsheet.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {

    viewModel { (tab: Tab) -> ContentsViewModel(tab, get()) }

    viewModel { (tab: Tab) -> HistoryViewModel(tab, get()) }
}