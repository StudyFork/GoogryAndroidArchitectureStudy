package study.architecture.myarchitecture.ui.main

import androidx.databinding.ObservableField
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.util.Filter

class MainViewModel(
    private val upbitRepository: UpbitRepository
) {

    val viewPagers = ObservableField<Array<String>>()

    val tabTitles = ObservableField<Array<String>>()

    val tabArraow = ObservableField<Filter.SelectArrow>()

    fun attatchView() {

    }

    fun detatchView() {

    }

    fun loadView() {

    }
}