package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val homeView: HomeContract.View
) : HomeContract.Presenter {

    private val dataSource = UpbitApiHandlerImpl

    private val disposables = CompositeDisposable()

    init {
        homeView.presenter = this
    }

    override fun subscribe() {
        loadMarketData()
    }

    override fun unSubscribe() {
        disposables.clear()
    }

    private fun loadMarketData() {
        homeView.showProgressBar(true)

        disposables.add(dataSource
            .getMarketList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    homeView.showProgressBar(false)
                    homeView.updateViewPager(response)
                },
                {
                    homeView.showProgressBar(false)
                    homeView.showToast("서버에서 데이터를 가져오는데 실패하였습니다.")
                }
            )
        )
    }
}