package com.jskim5923.architecturestudy.di.main

import androidx.lifecycle.ViewModel
import com.jskim5923.architecturestudy.di.viewmodel.ViewModelKey
import com.jskim5923.architecturestudy.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}