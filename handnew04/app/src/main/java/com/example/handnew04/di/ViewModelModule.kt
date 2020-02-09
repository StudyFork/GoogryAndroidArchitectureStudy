package com.example.handnew04.di

import com.example.handnew04.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelDataModule = module { single<MainViewModel> { MainViewModel(get()) } }