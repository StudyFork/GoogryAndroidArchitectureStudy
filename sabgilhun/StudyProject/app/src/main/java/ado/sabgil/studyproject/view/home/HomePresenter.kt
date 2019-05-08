package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl

class HomePresenter(
    private val homeView: HomeContract.View
) : HomeContract.Presenter {

    private val dataSource = UpbitApiHandlerImpl

    init {
        homeView.presenter = this
    }

    override fun loadMarketData() {
        homeView.showProgressBar(true)

        dataSource.getMarketList(
            {
                homeView.showProgressBar(false)
                homeView.updateViewPager(it)
            },
            {
                homeView.showProgressBar(false)
                homeView.showToast(it.message ?: "error")
            }
        )
    }
}