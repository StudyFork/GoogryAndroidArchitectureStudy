package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BasePresenter

class HomePresenter(
    private val homeView: HomeContract.View,
    private val coinRepository: CoinRepository
) : BasePresenter<HomeContract.View>(homeView), HomeContract.Presenter {

    override fun loadMarketList() {
        homeView.showProgressBar(true)

        disposables.add(coinRepository.loadMarketList(
            { response ->
                homeView.showProgressBar(false)
                homeView.updateViewPager(response)
            },
            {
                homeView.showProgressBar(false)
                homeView.showToastMessage(it)
            }
        ))
    }

}