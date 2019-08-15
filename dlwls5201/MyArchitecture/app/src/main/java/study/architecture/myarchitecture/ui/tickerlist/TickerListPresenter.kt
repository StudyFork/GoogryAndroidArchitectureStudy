package study.architecture.myarchitecture.ui.tickerlist

import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.model.mapToPresentation
import timber.log.Timber

class TickerListPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: TickerListContract.View,
    private val keyMarket: String,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : TickerListContract.Presenter {

    override fun createdView() {
        loadData()
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun loadData() {

        upbitRepository
            .getTickers(keyMarket)
            .doOnSubscribe {
                view.showProgress()
            }
            .doOnTerminate {
                view.hideProgress()
            }
            .subscribe({

                view.showTickers(it.mapToPresentation().toMutableList())

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}