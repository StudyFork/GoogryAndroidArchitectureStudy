package study.architecture.myarchitecture.ui.main

import study.architecture.myarchitecture.data.repository.UpbitRepository

class MainPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: MainContract.View
) : MainContract.Presenter {


}